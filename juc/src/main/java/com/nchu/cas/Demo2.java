package com.nchu.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Decription 对 Demo 进行优化，使用锁保证数据一致性
 * Q:但是耗时比较长，有没有办法缩短耗时？
 * 可以降低锁粒度
 * @Author yangsj
 * @Date 2020/7/28 10:38
 **/
public class Demo2 {
    // 总访问量
    private static int count;

    /**
     * @Description 加一操作（加锁）
     * @Author yangsj
     * @Date 2020/7/28 11:30
     **/
    public static synchronized void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }

    /**
     * @Description 模拟并发请求
     * @Author yangsj
     * @Date 2020/7/28 11:03
     **/
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        long begin = System.currentTimeMillis();
        // 模拟100个请求
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);

                    // 1 个请求做10次加法
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }).start();
        }
        // 等待所有线程执行完毕
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end - begin) + " count值："+ count);
    }
}
