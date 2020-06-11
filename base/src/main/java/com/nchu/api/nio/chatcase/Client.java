package com.nchu.api.nio.chatcase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/6/11 18:45
 **/
public class Client {
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
        System.out.println(socketChannel.getLocalAddress()+"客户端连接成功，开始发送数据！");

        // 监听其他客户端发送的数据

        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        selector.select();

                        Set<SelectionKey> selectionKeys = selector.selectedKeys();

                        Iterator<SelectionKey> iterator = selectionKeys.iterator();

                        while (iterator.hasNext()){

                            SelectionKey key = iterator.next();
                            if(key.isReadable()){
                                SocketChannel channel = (SocketChannel)key.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                channel.read(byteBuffer);
                                System.out.println("~~~~"+new String(byteBuffer.array()));
                                byteBuffer.clear();
                            }
                            iterator.remove();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());
            socketChannel.write(byteBuffer);
        }
    }
}
