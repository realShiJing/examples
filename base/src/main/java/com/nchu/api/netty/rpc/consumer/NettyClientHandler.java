package com.nchu.api.netty.rpc.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/15 16:08
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    // 与服务建立连接的上下文
    private static ChannelHandlerContext context;

    // 调用远程服务发送的消息
    private String message;

    // 远程调用返回结果
    private String result;
    /**
     * 与服务提供方建立连接完成后，初始化 ctx
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与服务器建立连接成功！");
        context = ctx;
    }

    /**
     * @Description 远程服务返回结果
     * @Author yangsj
     * @Date 2020/6/15 16:09
     **/
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("读取远程服务返回结果");
        result = (String)msg;
        System.out.println("唤醒阻塞任务并返回");
        //唤醒阻塞任务
        notify();
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


    /**
     * @Description 执行任务
     * @Author yangsj
     * @Date 2020/7/3 11:40
     **/
    @Override
    public synchronized Object call() throws Exception {
        //发送消息到远程服务
        System.out.println("发送消息！");
        context.writeAndFlush(message);
        System.out.println("进入等待");
        //等待远程服务返回结果
        wait();
        System.out.println("拿到返回结果");
        //唤醒该线程后，返回结果
        return result;
    }

    public void setMessage(Object message) {
        this.message = (String)message;
    }

}

