package com.nchu.cyclicBarrier;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Decription 模拟屏障被打破的场景
 * @Author yangsj
 * @Date 2020/7/21 15:30
 **/
public class CyclicBarrierTest02 {

    /**
     * @Description 模拟运动员
     * @Author yangsj
     * @Date 2020/7/21 15:35
     **/
    class Runner1 implements Runnable{

        private CyclicBarrier cyclicBarrier;
        private String name;

        Runner1(CyclicBarrier cyclicBarrier,String name){
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name + "开始准备");
            try {
                Thread.currentThread().sleep(50);
                System.out.println(name + "准备完毕！等待发令枪");
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    @Test
    public void test01() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("发令枪响了，跑！");
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new Runner1(barrier, "运动员" + i + "号")).start();
        }
        //等待子线程全部执行完
        Thread.currentThread().sleep(5000);
    }

    /**
     * @Description 模拟运动员
     * @Author yangsj
     * @Date 2020/7/21 15:35
     **/
    class Runner2 implements Runnable{

        private CyclicBarrier cyclicBarrier;
        private String name;

        Runner2(CyclicBarrier cyclicBarrier,String name){
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(name + "开始准备");
            try {
                Thread.currentThread().sleep(new Random().nextInt(100));
                System.out.println(name + "准备完毕！在起跑线等待发令枪");
                try {
                    cyclicBarrier.await();
                    System.out.println(name + "跑完了路程！");
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                    System.out.println(name+"看不见起跑线了");
                }
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    
    /**
     * @Description 线程挂起过后 屏障被打破，线程唤醒后会抛出异常，执行失败
     * @Author yangsj
     * @Date 2020/7/21 16:41
     **/
    @Test
    public void test02() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("发令枪响了，跑！");
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new Runner2(barrier, "运动员" + i + "号")).start();
        }
       Thread.currentThread().sleep(90);
        System.out.println("=========重置屏障=========");
        barrier.reset();
        Thread.currentThread().sleep(100);
        int numberWaiting = barrier.getNumberWaiting();
        System.out.println("还有"+numberWaiting+"个线程在等待中");
    }
    /**
     * @Description 子线程线程执行完毕执行后，执行最后一个任务过程中抛出异常，子线程也会抛出异常执行失败
     * @Author yangsj
     * @Date 2020/7/21 16:41
     **/
    @Test
    public void test03() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                String str = null;
                str.substring(0, 1);
                System.out.println("发令枪响了，跑！");
            }
        });


        for (int i = 0; i < 5; i++) {
            new Thread(new Runner2(barrier, "运动员" + i + "号")).start();
        }
        Thread.currentThread().sleep(1000);
    }

}
