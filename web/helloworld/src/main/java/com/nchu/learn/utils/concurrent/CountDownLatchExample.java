package com.nchu.learn.utils.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: 在构造CountDownLatch的时候需要传入一个整数n，在这个整数“倒数”到0之前，主线程需要等待在门口，而这个“倒数”过程则是由各个执行线程驱动的，每个线程执行完一个任务“倒数”一次
 * 总结来说，CountDownLatch的作用就是等待其他的线程都执行完任务，必要时可以对各个任务的执行结果进行汇总，然后主线程才继续往下执行。
 * @auther: yangsj
 * @created: 2019/2/12 14:27
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Service service = new Service(latch);

        for(int i  = 0; i < 5 ; i++){
            new Thread(service).start();
        }

        System.out.println("main  thread  await .");
        latch.await();
        System.out.println("main thread finishes await .");
    }

}

class Service implements Runnable {

    CountDownLatch latch = null;

    public Service(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+ "execute task");
            sleep(2);
            System.out.println(Thread.currentThread().getName()+ "finish task");
        } finally {
            latch.countDown();
        }

    }
    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

