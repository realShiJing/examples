package com.nchu.api.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Decription Netty 服务端实现，用于测试自定义编解码器
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
                            // ！！！ 编解码器加入的顺序没有要求
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //加入自定义解码器，用于解析客户端发送数据(入栈)
                            pipeline.addLast("decode",new MyByteToMessageDecoder());
                            //加入自定义编码器，用于将服务器发送数据组装为字节数组（出栈）
                            pipeline.addLast("encode", new MyMessageToByteEncoder());
                            //栈顶
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
