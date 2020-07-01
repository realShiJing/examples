package com.nchu.api.netty.tcp.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Decription 处理客户端请求的 handler
 * @Author yangsj
 * @Date 2020/6/15 15:47
 **/
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    int count;

    /**
     * @Description 读取客户端 发送数据
     * @Author yangsj
     * @Date 2020/6/15 15:53
     **/
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        System.out.println("客户端：" + channelHandlerContext.channel().remoteAddress() + "发来的消息是====>>" + messageProtocol);
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
