package com.nchu.api.netty.quickstart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Decription 处理客户端请求的 handler
 * @Author yangsj
 * @Date 2020/6/15 15:47
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @Description 读取客户端 发送数据
     *    1. ChannelHandlerContext ctx:上下文对象, 含有 管道pipeline , 通道channel, 地址
     *    2. Object msg: 就是客户端发送的数据 默认Object
     *    3. 可以使用提交任务的形式，异步返回通知客户端
     *    4. 假如业务逻辑处理比较耗时，服务器的work线程池中处理线程数又有限，这时大量客户端请求过来时会造成阻塞
     *    只有线程池用有空闲的线程，才会处理新的客户端连接，
     * @Author yangsj
     * @Date 2020/6/15 15:53
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 任务队列
        // 每个 NioEventLoop 中包含有一个 Selector，一个 taskQueue
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
              myHandler(ctx,msg);
            }
        });
        System.out.println("============================");
       /* myHandler(ctx,msg);*/
    }

    /**
     * @Description  自定义业务处理
     * @Author yangsj
     * @Date 2020/6/16 10:17 下午
     **/
    public void myHandler(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;
        try {
            System.out.println("服务器读取线程（"+Thread.currentThread().getName()+"）休眠20秒");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("客户端："+ctx.channel().remoteAddress()+"发来的消息是：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * @Description 数据读取完毕，向客户端发送数据
     * @Author yangsj
     * @Date 2020/6/15 15:54
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));

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
