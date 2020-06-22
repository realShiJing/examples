package com.nchu.api.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Decription 群聊服务器，用于转发客户端数据到其他客户端
 * @Author yangsj
 * @Date 2020/6/22 10:11
 **/
public class GroupChatServer {
    private Integer port;
    GroupChatServer (Integer port){
        this.port = port;
    }
    
    public void run() {
        NioEventLoopGroup bossGrop = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGrop = new NioEventLoopGroup();
        try{

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrop, workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());

                            pipeline.addLast(new GroupChatServerHandler());

                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException exception){
            System.out.println(exception);
        }finally {
            bossGrop.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer(8000);
        groupChatServer.run();

    }

}
