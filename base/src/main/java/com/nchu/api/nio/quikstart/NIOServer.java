package com.nchu.api.nio.quikstart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Decription 服务端，用户处理客户端 Socket 请求
 * @Author yangsj
 * @Date 2020/6/10 11:28
 **/
public class NIOServer {

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
            System.out.println("selector管理key的数量："+selector.keys().size());
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
                    System.out.println("客户端连接成功 :"+ channel.hashCode());
                    // 设置为非阻塞状态
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }
                // 客户端第一次连接成功后，传输数据时还会进入，状态并变为可读
                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    channel.read(byteBuffer);
                    System.out.println("客户端传输的数据:"+new String(byteBuffer.array()));
                }
                // 从 selectkeys 集合中删除处理过的key,防止重复处理
                iterator.remove();
            }
        }


    }
}
