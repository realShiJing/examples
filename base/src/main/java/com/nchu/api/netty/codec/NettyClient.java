package com.nchu.api.netty.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Decription Netty 客户端实现，用于测试自定义编解码器
 * @Author yangsj
 * @Date 2020/6/15 15:59
 **/
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try{
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //加入自定义Long类型编码器
                            pipeline.addLast("encode", new MyMessageToByteEncoder());
                            //加入自定义Long类型解码器
                            pipeline.addLast("decode", new MyByteToMessageDecoder());
                            // 编码器一定要加在发送数据Hadler之前
                            // 数据发送对应着pipeline 中处理器的出栈，从栈尾开始执行
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端 ok~");
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6668).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }
}
