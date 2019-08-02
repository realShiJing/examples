package com.nchu.api.netty.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;

/**
 * @description: 实现了服务器的业务逻辑，决定了连接创建后和接收到信息后该如何处理
 * @auther: yangsj
 * @created: 2019/2/15 16:07
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *@description: 每个信息入站都会调用
     *@auther: yangsj
     *@created: 2019/2/15 16:31
     *
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx ,Object msg){
        ByteBuffer byteBuffer = (ByteBuffer) msg;

        System.out.println("Server received : " + byteBuffer.toString());

        ctx.write(byteBuffer);

    }
    /**
     *@description: 通知处理器最后的 channelread() 是当前批处理中最后一条消息时调用
     *@auther: yangsj
     *@created: 2019/2/15 16:36
     *
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        //刷新所有待审消息到远程节点。关闭通道后，操作完成
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
        .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     *@description: 读操作时捕获到异常时调用
     *@auther: yangsj
     *@created: 2019/2/15 16:44
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx , Throwable cause){
        //打印异常堆栈跟踪
        cause.printStackTrace();
        //关闭通道
        ctx.close();
    }
}
