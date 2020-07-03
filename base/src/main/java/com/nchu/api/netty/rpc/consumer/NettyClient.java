package com.nchu.api.netty.rpc.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/15 15:59
 **/
public class NettyClient {

    private static NettyClientHandler clientHandler;

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    /**
     * @Description 初始化Netty客户端
     * @Author yangsj
     * @Date 2020/7/3 11:19
     **/
    public static void initClient() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        clientHandler = new NettyClientHandler();
        try{
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(clientHandler);
                        }
                    });
            System.out.println("服务消费者  ok~");
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6668).sync();
            //cf.channel().closeFuture().sync();实现rpc不能调用该方法？？？
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
           // group.shutdownGracefully();实现rpc不能调用该方法？？？
        }
    }

    /**
     * 生成远程代理对象
     * @param serviceClass 代理对象的接口
     * @return
     */
    public Object getBean(Class<?> serviceClass){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass},((proxy, method, args) -> {
                    //如果还未与服务提供者建立连接， 初始化netty客户端
                    if(clientHandler == null){
                        initClient();
                    }
                    //设置调用请求参数
                    clientHandler.setMessage(method.getName()+"#"+args[0]);
                    //提交任务，服务提供者处理完毕后返回处理结果
                    return  poolExecutor.submit(clientHandler).get();
                }));
    }
}
