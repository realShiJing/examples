package com.nchu.api.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/17 18:55
 **/
public class NettyHttpServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boosGrop = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGrop = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGrop,workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyHttpChannelInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8000).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGrop.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }

    }
}
