package com.nchu.api.netty.quickstart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/15 16:08
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接到服务端后，开始发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello Server!",CharsetUtil.UTF_8));
    }

    /**
     * @Description 读取服务端发送数据
     * @Author yangsj
     * @Date 2020/6/15 16:09
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端发送数据："+ byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址："+ ctx.channel().remoteAddress());
    }



    /**
     * @Description 数据读取完毕
     * @Author yangsj
     * @Date 2020/6/15 16:21
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("数据读取完毕~");
    }


    /**
     * @Description 发生异常
     * @Author yangsj
     * @Date 2020/6/15 16:22
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().closeFuture();
    }
}

