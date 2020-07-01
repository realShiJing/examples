package com.nchu.api.netty.tcp.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/15 16:08
 **/
public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    /**
     * 连接到服务端后，开始发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String message = "你好，我是realshijing!";
        MessageProtocol messageProtocol = new MessageProtocol();
        //设置传输数据长度
        messageProtocol.setLen(message.getBytes().length);
        //设置传输数据字节内容
        messageProtocol.setContent(message.getBytes());
        // 重复写多次数据给服务端
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(messageProtocol);
        }
    }

    /**
     * @Description 读取服务端发送数据
     * @Author yangsj
     * @Date 2020/6/15 16:09
     **/
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        System.out.println("服务器地址："+ channelHandlerContext.channel().remoteAddress()+"服务端返回数据："+ messageProtocol);
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

