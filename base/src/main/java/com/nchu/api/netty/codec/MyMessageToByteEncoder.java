package com.nchu.api.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Decription 自定义 Long 类型编码器
 * @Author yangsj
 * @Date 2020/6/30 15:55
 **/
public class MyMessageToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * @Description 对需要发送的数据进行编码
     * @Author yangsj
     * @Date 2020/6/30 16:14
     **/
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {
        System.out.println("编码器被调用！");
        //将Long类型的数值以字节的方式写入到缓冲区
        byteBuf.writeLong(aLong);
    }
}
