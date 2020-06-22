package com.nchu.api.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;

/**
 * @Decription 客户端与服务器之间传递的参数类型为String
 * @Author yangsj
 * @Date 2020/6/22 10:29
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {


    /**
     * @Description 必须是静态变量，因为每个客户端连接都是新生成一个 GroupChatServerHandler 实例
     *  静态变量能够保证，同一个类的不同对象共享该变量
     * @Author yangsj
     * @Date 2020/6/22 14:37
     **/
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel clientChannel = ctx.channel();
        SocketAddress socketAddress = clientChannel.remoteAddress();
        // 向其他客户端发送数据
        channelGroup.writeAndFlush("客户"+socketAddress.toString()+"加入群聊!");
        // 将自己加入客户端通道组中
        channelGroup.add(clientChannel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"断开！");
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线！");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        Channel clientChannel = channelHandlerContext.channel();
        SocketAddress socketAddress = clientChannel.remoteAddress();
        for (Channel channel : channelGroup) {
            if(clientChannel != channel){
                channel.writeAndFlush("客户"+socketAddress.toString()+"发送数据："+msg);
            }
        }
    }

}
