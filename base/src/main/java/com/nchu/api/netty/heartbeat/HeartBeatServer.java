package com.nchu.api.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Decription 心跳测试
 * @Author yangsj
 * @Date 2020/6/23 14:07
 **/
public class HeartBeatServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGrop = new  NioEventLoopGroup();
        NioEventLoopGroup workerGrop = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrop, workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))//增加日志输出
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            /**
                             * 1. IdleStateHandler 是netty 提供的处理空闲状态的处理器
                             * 2. long readerIdleTime : 表示多长时间没有读, 就会发送一个心跳检测包检测是否连接
                             * 3. long writerIdleTime : 表示多长时间没有写, 就会发送一个心跳检测包检测是否连接
                             * 4. long allIdleTime : 表示多长时间没有读写, 就会发送一个心跳检测包检测是否连接
                             * 当 IdleStateEvent 触发后 , 就会传递给管道 的下一个handler去处理
                             * 通过调用(触发)下一个handler 的 userEventTiggered , 在该方法中去处理 IdleStateEvent(读空闲，写空闲，读写空闲)
                             */
                            pipeline.addLast(new IdleStateHandler(3, 5, 6, TimeUnit.SECONDS));
                            pipeline.addLast(new HeartBeatHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(6668).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            bossGrop.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }


    }
}
