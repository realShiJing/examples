package com.nchu.test;

import org.junit.Test;

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


    int a = 1 ;
    volatile boolean flag = false;
    int ret = 0 ;
    public void write(){
        a = 2;      //1
        flag = true;//2
    }

    public void multiply(){
        if(flag){         //3
            ret = a*a;    //4
        }
    }


    /**
     * @Description 有序性测试
     * 经测试，该种场景下不存在1和2步骤重排序的问题
     * @Author yangsj
     * @Date 2019/10/18 13:56
     **/
    @Test
    public void OrderlyTest(){
        for (int i = 0; i < 100 ; i++) {
            VolatileTest test = new VolatileTest();
            CountDownLatch latch = new CountDownLatch(2);
            //线程A执行对数据和flag状态的改变
            new Thread(() -> {
                test.write();
                latch.countDown();
            }).start();
            //线程B判断flag状态，并处理数据
            new Thread(() -> {
                test.multiply();
                latch.countDown();
            }).start();
            try {
                //等待两个线程执行完毕
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(test.ret);
        }
    }
}
