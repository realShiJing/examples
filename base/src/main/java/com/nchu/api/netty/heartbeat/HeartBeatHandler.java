package com.nchu.api.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/23 14:25
 **/
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
        String state = "";
        switch (idleStateEvent.state()){
            case READER_IDLE:
                state = "读超时";
                break;
            case WRITER_IDLE:
                state = "写超时";
                break;
            case ALL_IDLE:
                state = "读写超时";
                break;
            default:
                break;
        }
        System.out.println(ctx.channel().remoteAddress()+"发生"+state);
    }
}
