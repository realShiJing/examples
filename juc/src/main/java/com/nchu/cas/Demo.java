package com.nchu.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Decription 需求：我们开发一个网站，需要对访问量进行统计，用户每发送一次请求，访问量+1
 *如何实现？
 *  我们模拟有100个人同时访问，并且每个人对咱们的网站发起10次请求，最后总访问次数应该是1000次。
 *Q：存在什么问题？
 *A: 最终计算得到的count值小于1000
 *Q；为什么会产生这个问题？
 *A: count ++ 实际上是由三步组成的。（jvm执行引擎）
 *   1. 获取count的值，记做：A = count
 *   2. 将count加1,得到B: B = A + 1
 *   3. 将B赋值给count
 *   在并发场景下，如果有两个线程同时执行到第一步，获取到相同的count值，然后将count值加一赋值给count
 *   两个线程执行完后，count只加了1 而不是 加2 ，导致count最终计算结果不正确。
 *
 *Q:怎么解决结果不正确的问题？
 *A：对count++操作的时候，我们让多个线程排队处理，多个线程同时到达request()方法时，只允许一个线程进去操作
 * 其他的线程在外面等待，等里面的线程操作完成退出后，等待的线程中再进去一个，这样操作的count++就是排队进行的，
 * 一定能保证count值最终是正确的
 *
 * Q:怎么实现排队效果？
 * A:java中synchronized 关键字和ReentrantLock都可以对资源加锁，保证并发正确性
 * 多线程环境下可以保证被锁住的资源可以串行访问。
 *
 * @Author yangsj
 * @Date 2020/7/28 10:38
 **/
public class Demo {
    // 总访问量
    private static int count;

    /**
     * @Description 加一操作
     * @Author yangsj
     * @Date 2020/7/28 11:30
     **/
    public static void request(){
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
