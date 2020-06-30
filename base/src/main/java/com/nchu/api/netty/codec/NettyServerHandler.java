package com.nchu.api.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Decription 处理客户端请求的 handler
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

        System.out.println("客户端："+ctx.channel().remoteAddress()+"发来的消息是：" + msg);

        // 给客户端发送一个Long 类型的数值
        ctx.writeAndFlush(12232L);
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
