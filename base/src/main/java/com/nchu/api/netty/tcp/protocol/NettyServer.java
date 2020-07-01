package com.nchu.api.netty.tcp.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Decription Netty 服务端实现，自定义解析协议包，用于解决粘包、拆包问题
 * @Author yangsj
 * @Date 2020/6/15 14:54
 **/
public class NettyServer {
    public static void main(String[] args) {

        EventLoopGroup boosGrop = new NioEventLoopGroup(1);
        EventLoopGroup workGrop = new NioEventLoopGroup(2);

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGrop, workGrop)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new MessageProtocolDecoder());
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器 is ready");
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
