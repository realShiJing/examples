package com.nchu.api.netty.rpc.provider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Decription 处理消费者请求的 handler
 * @Author yangsj
 * @Date 2020/6/15 15:47
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @Description 读取服务消费者 发送的数据
     * @Author yangsj
     * @Date 2020/6/15 15:53
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //截取以特定协议头开始的消息
        String[] requests = msg.toString().split("#");
        // 要调用的方法
        String method = requests[0];
        //需要传递的参数
        String message = requests[1];
        if(method.equals("rpcQuest")){
            //新建服务，并调用返回结果
            String result = new RpcServiceImpl().rpcQuest(message);
            //将结果返回服务消费者
            ctx.writeAndFlush(result);
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
