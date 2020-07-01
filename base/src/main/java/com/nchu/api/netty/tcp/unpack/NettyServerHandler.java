package com.nchu.api.netty.tcp.unpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @Decription 处理客户端请求的 handler
 * @Author yangsj
 * @Date 2020/6/15 15:47
 **/
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    int count;

    /**
     * @Description 读取客户端 发送数据
     * @Author yangsj
     * @Date 2020/6/15 15:53
     **/
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        int len = msg.readableBytes();
        System.out.println("length:" +len);
        byte[] bytes = new byte[len];
        msg.readBytes(bytes);
        System.out.println("客户端：" + ctx.channel().remoteAddress() + "发来的消息是：" + new String(bytes, Charset.forName("utf-8")));
        System.out.println("接收次数："+(++count));
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
