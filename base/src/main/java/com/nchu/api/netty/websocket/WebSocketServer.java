package com.nchu.api.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/24 14:49
 **/
public class WebSocketServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGrop = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGrop = new NioEventLoopGroup();
        try{
            ServerBootstrap  serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrop, workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                           /* pipeline.addLast(new HttpServerCodec());*/
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(8129));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            pipeline.addLast(new WebSocketHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }finally {
            bossGrop.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }

    }
}
