package com.nchu.api.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Decription 传统的 IO 网络编程演示（同步阻塞）
 * @Author yangsj
 * @Date 2020/6/7 2:36 下午
 **/
public class BIOServer {
    /**
     * @Description  ServerSocket 服务
     *
     * 使用 telnet 127.0.0.1 6666 命令测试
     * @Author yangsj
     * @Date 2020/6/7 2:44 下午
     **/
    public static void main(String[] args) throws IOException {
        //开启 socket 服务，监听 6666 端口
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("socket 服务启动");
        // 阻塞点，等待客户端连接
        for (;;){
            Socket socket = serverSocket.accept();

            // 使用线程池创建线程处理客户端的连接
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    10,
                    20,
                    100,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(100));

            threadPoolExecutor.execute(()->{
                handle(socket);
            });
        }
    }

    /**
     * @Description  处理客户端的 Socket 连接
     * @Author yangsj
     * @Date 2020/6/7 2:44 下午
     **/
    private static void handle(Socket socket){
        InputStream inputStream = null;
        try {
            System.out.println("线程>>>"+Thread.currentThread().getName()+"开始处理客户端连接！");
            // 阻塞点： 获取客户端的传输的内容
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            for(;;){
                //打印客户端传输内容
                int len = inputStream.read(bytes);
                if(len!=-1){
                    System.out.println(new String(bytes));
                }else {
                    System.out.println("客户端退出！");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("客户端关闭连接！");
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
