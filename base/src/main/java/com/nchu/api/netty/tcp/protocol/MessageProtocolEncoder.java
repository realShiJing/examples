package com.nchu.api.netty.tcp.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Decription 自定义协议对象编码
 * @Author yangsj
 * @Date 2020/7/1 15:12
 **/
public class MessageProtocolEncoder extends MessageToByteEncoder<MessageProtocol> {
    /**
     *  将协议包封装对象，拆分为数据长度和数据内容发送
     * @param channelHandlerContext
     * @param messageProtocol
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol, ByteBuf byteBuf) throws Exception {
        //将数据连接通道中的缓冲区
        byteBuf.writeInt(messageProtocol.getLen());
        byteBuf.writeBytes(messageProtocol.getContent());
    }
}
