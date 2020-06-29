package com.nchu.api.netty.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/15 16:08
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接到服务端后，开始发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送一个 protobuf协议封装的Student对象到服务端
       /* StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(1).setName("wayfo").build();
        ctx.writeAndFlush(student);*/

       //随机发送 Student 或者 Worker对象
        int random = new Random().nextInt(3);
        MyDataInfo.Mymessage message = null;
        if(random == 0){
            message = MyDataInfo.Mymessage.newBuilder().setDataType(MyDataInfo.Mymessage.DataType.StudentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(2).setName("wayfo").build()).build();
        }else {
            message = MyDataInfo.Mymessage.newBuilder().setDataType(MyDataInfo.Mymessage.DataType.WorkerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setAge(21).setSalary("1000USD").build()).build();
        }

        ctx.writeAndFlush(message);
    }

    /**
     * @Description 读取服务端发送数据
     * @Author yangsj
     * @Date 2020/6/15 16:09
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器地址："+ ctx.channel().remoteAddress()+"服务端返回数据："+ byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * @Description 发生异常
     * @Author yangsj
     * @Date 2020/6/15 16:22
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().closeFuture();
    }
}

