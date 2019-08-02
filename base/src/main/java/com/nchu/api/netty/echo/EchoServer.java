package com.nchu.api.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/2/15 16:50
 */
public class EchoServer {

    private  final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public  void run (String[] args) throws InterruptedException {
        int port = 8080;
        new EchoServer(8080).start();
    }

    public  void start () throws InterruptedException {
        //NioEventLoopGroup 接受和处理新连接
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)//指定 NioServerSocketChannel为信道类型
                    .localAddress(new InetSocketAddress(this.port))//设置 socket 地址使用所选的端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {//当一个新的连接被接受，一个新的子 Channel 将被创建， ChannelInitializer 会添加我们EchoServerHandler 的实例到 Channel 的 ChannelPipeline
                        @Override
                        //添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //等待服务器绑定完成,（调用 sync() 的原因是当前线程阻塞）
            ChannelFuture channelFuture =  serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + channelFuture.channel().localAddress());
            // 等待服务器 Channel 关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            group.shutdownGracefully().sync();
        }
    }
}
