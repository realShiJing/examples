package com.nchu.api.netty.tcp.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Decription 自定义协议对象解码
 * @Author yangsj
 * @Date 2020/7/1 15:16
 **/
public class MessageProtocolDecoder extends ByteToMessageDecoder {

    /**
     * @Description 解析数据，并组装为自定义协议对象
     * @Author yangsj
     * @Date 2020/7/1 15:19
     **/
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        MessageProtocol messageProtocol = new MessageProtocol();
        //从连接通道中的缓冲区读取数据
        int length = byteBuf.readInt();

        messageProtocol.setLen(length);

        byte[] content = new byte[length];

        byteBuf.readBytes(content);

        messageProtocol.setContent(content);

        //交个下个处理器处理
        list.add(messageProtocol);
    }
}
