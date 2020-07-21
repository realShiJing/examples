package com.nchu.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Decription 源码阅读
 * 线程 t3 和线程 t4 等待线程 t1 和线程 t2 执行完毕后开始执行自己的任务
 * @Author yangsj
 * @Date 2020/7/20 11:00
 **/
public class CountDownLatchTest03 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        t1.start();
        t2.start();


        Thread t3 = new Thread(()->{
            try {
                latch.await();
                System.out.println("线程t3从await()中返回了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(()->{
            try {
                latch.await();
                System.out.println("线程t4从await()中返回了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t4.start();
    }
}
