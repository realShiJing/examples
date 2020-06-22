package com.nchu.api.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;


/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/22 11:34
 **/
public class GroupChatClient {
    private String address = "127.0.0.1";
    private Integer port = 8000;
    GroupChatClient(String address,Integer port){
        this.address = address;
        this.port = port;
    }
    public  void run()  {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try{

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new GroupChatHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(address, port).sync();
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
            channel.closeFuture().sync();
        }catch (InterruptedException exception){
            System.out.println(exception);
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        GroupChatClient groupChatClient = new GroupChatClient("127.0.0.1",8000);
        groupChatClient.run();
    }
}
