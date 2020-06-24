package com.nchu.api.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/24 15:09
 **/
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame("服务器时间："+ LocalDateTime.now() +">>"+ textWebSocketFrame.text()));
    }
}
