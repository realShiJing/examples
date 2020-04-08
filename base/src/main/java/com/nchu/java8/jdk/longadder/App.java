package com.nchu.java8.jdk.longadder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Decription LongAdder 测试
 * @Author yangsj
 * @Date 2020/4/8 15:22
 **/
public class App {

    /**
     * @Description 随着并发线程越来越多 LongAdder 的优势就越明显
     * @Author yangsj
     * @Date 2020/4/8 15:57
     **/
    public static void main(String[] args){
        //testAtomicLongVSLongAdder(10, 100000);
        testAtomicLongVSLongAdder(40, 2000000);
        //testAtomicLongVSLongAdder(20, 500000);
    }

    static void testAtomicLongVSLongAdder(final int threadCount, final int times){
        try {
            long start = System.currentTimeMillis();
            testLongAdder(threadCount, times);
            long end = System.currentTimeMillis() - start;
            System.out.println("条件>>>>>>线程数:" + threadCount + ", 单线程操作计数" + times);
            System.out.println("结果>>>>>>LongAdder方式增加计数" + (threadCount * times) + "次,共计耗时:" + end);

            long start2 = System.currentTimeMillis();
            testAtomicLong(threadCount, times);
            long end2 = System.currentTimeMillis() - start2;
            System.out.println("条件>>>>>>线程数:" + threadCount + ", 单线程操作计数" + times);
            System.out.println("结果>>>>>>AtomicLong方式增加计数"+ (threadCount * times) +"次,共计耗时:" + end2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void testAtomicLong(final int threadCount, final int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = new ArrayList<>();
        for (int i=0;i<threadCount;i++){
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j<times; j++){
                        atomicLong.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }, "my-thread"+i));
        }

        for (Thread thread : list){
            thread.start();
        }
        countDownLatch.await();
    }

    static void testLongAdder(final int threadCount, final int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        LongAdder longAdder = new LongAdder();
        List<Thread> list = new ArrayList<>();
        for (int i=0;i<threadCount;i++){
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j<times; j++){
                        longAdder.add(1);
                    }
                    countDownLatch.countDown();
                }
            }, "my-thread"+i));
        }

        for (Thread thread : list){
            thread.start();
        }
        countDownLatch.await();
    }
}
