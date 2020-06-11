package com.nchu.api.nio.chatcase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/11 18:28
 **/
public class Server {

    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 获取 Selector
        Selector selector = Selector.open();

        // Socket 端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);

        // 绑定端口，在服务器端监听
        serverSocketChannel.socket().bind(inetSocketAddress);

        // 设置为非阻塞状态
        serverSocketChannel.configureBlocking(false);
        //将 serverSocketChannnel 注册到 Selector, 并开启准备接受 Socket 连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询等待客户端 Socket 连接
        while (true){
            // 有连接事件时，会通知 selector
            if(selector.select() == 0){
                System.out.println("------------");
            }
            // selector 管理的 SelectionKey 包含 server 和 后续建立连接的 client 的映射
            // 有客户端连接时，通过 Selector 获取 SelectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历 SelectionKeys ，处理客户端的连接
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                // 客户端连接成功，将客户端 SocketChannel 注册到 Selector，准备读取客户端传输内容
                if(key.isAcceptable()){
                    // 通过 serverSocketChannel 获取 客户端的连接
                    SocketChannel channel = serverSocketChannel.accept();
                    System.out.println(channel.getRemoteAddress().toString()+"上线~");
                    // 设置为非阻塞状态
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                }
                // 客户端第一次连接成功后，传输数据时还会进入，状态并变为可读
                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    try{
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.put((channel.getRemoteAddress().toString()+"：").getBytes());
                        channel.read(byteBuffer);
                        System.out.println("中转"+new String(byteBuffer.array()));

                        // 将客户端传输数据转发到其他客户端
                        // 获取注册到 selector 中所有的客户端
                        Set<SelectionKey> keys = selector.keys();
                        for (SelectionKey selectionKey : keys) {
                            Channel toChannel = selectionKey.channel();
                            // 排除自己，转发给他人
                            if(toChannel instanceof SocketChannel && toChannel != channel){
                                SocketChannel dest  =  (SocketChannel) toChannel;
                                dest.write(byteBuffer);
                            }
                            byteBuffer.clear();
                        }
                    }catch (IOException e){
                        System.out.println(channel.getRemoteAddress() + "离线了~");
                        key.cancel();
                    }
                }

                // 从 selectkeys 集合中删除处理过的key,防止重复处理
                iterator.remove();
            }
        }


    }
}
