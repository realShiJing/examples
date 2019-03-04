package com.nchu.learn.utils.netty.discard;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/1/30 15:47
 */
public class TimeClient {
    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
        int port = Integer.parseInt("8080");
        //如果只指定了一个 EventLoopGroup，那它就会即作为一个 boss group ，也会作为一个 workder group，尽管客户端不需要使用到 boss worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //Bootstrap 和 ServerBootstrap 类似,不过它是对非服务端的 channel 而言，比如客户端
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);//代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel 被创建时使用
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // 启动客户端
            ChannelFuture f = b.connect(host, port).sync();

            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
