package com.nchu.threadPool;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Decription 线程池测试
 * @Author yangsj
 * @Date 2020/2/28 14:40
 **/
public class App {

    /**
     * @Description 自定义线程测试
     * @Author yangsj
     * @Date 2020/2/28 14:51
     **/
    @Test
    public void test(){
        // 创建有界阻塞队列
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool threadPool = new MyThreadPool(10, queue);
        // 提交任务
        threadPool.execute(()->{
            System.out.println("Hello world !");
        });
    }
}
