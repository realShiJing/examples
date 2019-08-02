package com.nchu.api.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/1/30 13:52
 */
public class DiscardServerHandler  extends ChannelInboundHandlerAdapter {

    /**
     *@description: 每当从客户端收到新的数据时，这个方法会在收到消息时被调用
     *@auther: yangsj
     *@created: 2019/1/30 14:02
     *
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //读取客户端传入的数据
           ByteBuf buf = (ByteBuf) msg;
            while (buf.isReadable()) {
                System.out.print((char) buf.readByte());
            }

        } finally {
            ReferenceCountUtil.release(msg);
        }


//           回传数据
          /*  ctx.write(msg);
            ctx.flush();*/
    }

    /**
     *@description:事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉
     *@auther: yangsj
     *@created: 2019/1/30 14:04
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
