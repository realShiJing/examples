package com.nchu.api.netty.quickstart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/15 15:47
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @Description 读取客户端 发送数据
     * @Author yangsj
     * @Date 2020/6/15 15:53
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("客户端发送消息是：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ ctx.channel().remoteAddress());
    }


    /**
     * @Description 数据读取完毕，向客户端发送数据
     * @Author yangsj
     * @Date 2020/6/15 15:54
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));

    }

    /**
     * @Description 产生异常时，关闭
     * @Author yangsj
     * @Date 2020/6/15 15:56
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
