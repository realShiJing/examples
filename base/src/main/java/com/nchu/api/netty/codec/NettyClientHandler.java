package com.nchu.api.netty.codec;

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
        // 发送16个字节，解码器会解析两次，pipeline中的处理器链同样会调用两次
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
        //发送 Long 类型的数值
        System.out.println("Handler被调用，发送数据~");
        //ctx.writeAndFlush(1232L);
    }

    /**
     * @Description 读取服务端发送数据
     * @Author yangsj
     * @Date 2020/6/15 16:09
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器地址："+ ctx.channel().remoteAddress()+"服务端返回数据："+ msg);
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

