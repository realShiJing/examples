package com.nchu.api.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Decription 处理客户端请求的 handler
 * @Author yangsj
 * @Date 2020/6/15 15:47
 **/
//public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPOJO.Student> {
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Mymessage>{
   /* @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StudentPOJO.Student student) throws Exception {
        System.out.println("id:"+student.getId()+"   "+"name:"+student.getName());
    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.Mymessage mymessage) throws Exception {
        MyDataInfo.Mymessage.DataType dataType = mymessage.getDataType();
        if(dataType == MyDataInfo.Mymessage.DataType.StudentType){
            MyDataInfo.Student student = mymessage.getStudent();
            System.out.println("学生信息>>id:"+student.getId()+"  name:"+student.getName());
        }else if(dataType == MyDataInfo.Mymessage.DataType.WorkerType) {
            MyDataInfo.Worker worker = mymessage.getWorker();
            System.out.println("工人信息>>age:"+worker.getAge()+"  salary:"+worker.getSalary());

        }

    }
    /**
     * @Description 产生异常时，关闭
     * @Author yangsj
     * @Date 2020/6/15 15:56
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
