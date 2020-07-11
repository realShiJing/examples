package com.nchu.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/2/3 7:22 下午
 **/
public class App {
    /**
     * @Description  自定义线程池使用示例
     * @Author yangsj
     * @Date 2020/2/3 7:27 下午
     **/
    @Test
    public void test(){
        // 创建有界阻塞队列
        BlockingQueue workQueue = new LinkedBlockingQueue(2);
        // 创建线程池
        ThreadPool threadPool = new ThreadPool(3, workQueue);
        try {
            for (int i = 0; i < 6; i++) {
                // 提交任务
                threadPool.execute(()->{
                    System.out.print(Thread.currentThread());
                    System.out.println("--hello!");
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description  测试 java 中的线程池
     * @Author yangsj
     * @Date 2020/2/3 7:42 下午
     **/
    @Test
    public void test1(){
        //不建议使用 Executors 的最重要的原因是：Executors 提供的很多方法默认使用的都是无界的 LinkedBlockingQueue，高负载情境下，无界队列很容易导致 OOM
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        executorService.execute(()->{
            System.out.println(Thread.currentThread());
        });
    }

    /**
     * @Description  创建线程池的正确姿势
     * 避免使用Executors创建线程池，主要是避免使用其中的默认实现，
     * 那么我们可以自己直接调用ThreadPoolExecutor的构造函数来自己创建线程池。
     * 在创建的同时，给BlockQueue指定容量就可以了。
     * @Author yangsj
     * @Date 2020/2/3 8:08 下午
     **/
    @Test
    public void test2(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                20,
                120L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100));

        threadPoolExecutor.execute(()->{
            System.out.println(Thread.currentThread());
        });

    }
    /**
     * @Description  测试自定义 ThreadFactory
     * @Author yangsj
     * @Date 2020/2/3 7:54 下午
     **/
    @Test
    public void test3(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                100,
                120,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                runnable -> {
                    //给线程指定一个有意义的名字。
                    Thread thread = new Thread(runnable,"Customer-Name-Prefix" + runnable.hashCode());
                    return thread;
                },
                new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(()->{
            System.out.println(Thread.currentThread());
        });
    }
    
    /**
     * @Description 由 guava 提供的 ThreadFactoryBuilder 创建线程池
     * @Author yangsj
     * @Date 2020/2/3 8:12 下午
     **/
    @Test
    public void test4(){
        // 使用 guava 创建 threadFactory,自定义线程名
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                20,
                120L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.execute(()->{
            System.out.println(Thread.currentThread());
        });

    }
}
