package com.nchu.api.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/17 19:00
 **/
public class MyHttpHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     *  读取客户端请求
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);

        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
        channelHandlerContext.writeAndFlush(httpResponse);

    }
}
