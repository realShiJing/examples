package com.nchu.api.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/22 11:40
 **/
public class GroupChatHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg);
        //channelHandlerContext.writeAndFlush("hello ! i am "+channelHandlerContext.channel().remoteAddress().toString());

    }
}
