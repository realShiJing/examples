package com.nchu.api.nio.quikstart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Decription 客户端发送 Socket 请求
 * @Author yangsj
 * @Date 2020/6/10 16:03
 **/
public class NIOClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建一个 SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        // 尝试连接服务器
        socketChannel.connect(new InetSocketAddress("127.0.0.1",6666));

        // 如果连接失败
        if (!socketChannel.isConnected()){
            // 循环尝试连接，直到连接成功
            while (!socketChannel.finishConnect()){
                System.out.println("客户端未连接成功！");
            }
        }
        System.out.println("客户端连接成功，开始发送数据！");
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello NIO".getBytes());
        socketChannel.write(byteBuffer);
        new CountDownLatch(1).await();
    }
}
