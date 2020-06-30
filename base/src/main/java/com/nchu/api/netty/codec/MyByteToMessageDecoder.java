package com.nchu.api.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Decription 自定义解码器
 * @Author yangsj
 * @Date 2020/6/30 16:24
 **/
public class MyByteToMessageDecoder extends ByteToMessageDecoder {

    /**
     * @Description 自定义协议以8字节的长度解析 Long 类型的数据
     * @Author yangsj
     * @Date 2020/6/30 16:26
     **/
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码器被调用！");
        // Long 类型字节长度为8
        if(byteBuf.readableBytes() >=8){
            //读出8个字节长度的数据
            long aLong = byteBuf.readLong();
            //将解析后的数据加入集合中，向下传递，交给下一个Handler处理器处理
            list.add(aLong);
        }
    }
}
