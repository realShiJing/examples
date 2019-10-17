package com.nchu.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Decription volatile关键字的相关测试
 * @Author yangsj
 * @Date 20191016 16:00
 **/
public class VolatileTest {
    public /*volatile int*/ AtomicInteger inc = new AtomicInteger(0);

    public void increase() {
        // inc++;
        inc.getAndIncrement();
    }


    /**
     * @Description 测试结果，每次打印的的值都不一样，
     * 证明volatile修饰的变量在多线程的环境下无法保持原子性
     * @Author yangsj
     * @Date 2019/10/17 15:37
     **/
    public static void main(String[] args) throws InterruptedException {
        final VolatileTest test = new VolatileTest();
        CountDownLatch latch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<100000;j++){
                    test.increase();
                }
                latch.countDown();
            }).start();

        }
        latch.await();

        System.out.println(test.inc);
    }

}
