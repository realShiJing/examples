package com.nchu.api.netty.quickstart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Decription Netty 服务端实现
 * @Author yangsj
 * @Date 2020/6/15 14:54
 **/
public class NettyServer {
    public static void main(String[] args) {
        //1. NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器
        //2. 创建两个线程组 bossGroup 和 workerGroup
        //3. bossGroup 只是处理连接请求 , 真正的和客户端业务处理，会交给 workerGroup完成
        //4. 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上
        //5. bossGroup 和 workerGroup 含有的子线程(NioEventLoop)的个数和cpu核数有关
        //   默认实际 cpu核数 * 2
        EventLoopGroup boosGrop = new NioEventLoopGroup(1);
        EventLoopGroup workGrop = new NioEventLoopGroup(2);
        // NioEventLoopGroup 下包含多个 NioEventLoop
        // NioEventLoop 表示一个不断循环执行处理任务的线程，每个 NioEventLoop 都有一个 selector，用于监听绑定在其上的 socket 网络通道。

        try{
            //ServerBootstrap 是一个启动 NIO 服务的辅助启动类。
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置并绑定mianReactor和subReactor
            serverBootstrap.group(boosGrop, workGrop)
                    //使用NioSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //ChannelInitializer 是一个特殊的处理类，他的目的是帮助使用者配置一个新的 Channel。
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("客户 socketchannel hashcode= " + socketChannel.hashCode());
                            // 当轮训到准备就绪的channel后，由Reactor线程：NioEventLoop执行pipline中的方法，最终调度并执行channelHandler
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器 is ready");
            // 绑定端口，开始接收进来的连接
            ChannelFuture cf = serverBootstrap.bind(6668).sync();

            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGrop.shutdownGracefully();
            workGrop.shutdownGracefully();
        }


    }
}
