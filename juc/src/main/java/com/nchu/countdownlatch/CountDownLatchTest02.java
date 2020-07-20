package com.nchu.countdownlatch;

import java.util.concurrent.*;

/**
 * @Decription 子线程同一时间开始执行任务（发令枪）
 * @Author yangsj
 * @Date 2020/7/20 10:35
 **/
public class CountDownLatchTest02 {

    public static void main(String[] args) throws InterruptedException {
        // 子线程个数
        int threadNum = 8 ;
        // 子线程同一开始栅栏
        CountDownLatch startLatch = new CountDownLatch(1);
        // 主线程等待子线程执行完毕栅栏
        CountDownLatch doneLatch = new CountDownLatch(threadNum);

        // 创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8,
                10,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,"threadName"+r.hashCode());
                    }
                });
        for (int i = 0; i < threadNum; i++) {
            poolExecutor.execute(new Worker(startLatch,doneLatch,i));
        }
        startLatch.countDown();
        System.out.println("子线程统一开启");
        long startTime = System.currentTimeMillis();
        System.out.println("主线程等待子线程");
        doneLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("子线程执行完毕，总耗时"+(endTime - startTime));

    }
    static class Worker implements Runnable{
        CountDownLatch startLatch;
        CountDownLatch doneLatch;
        int taskId;
        Worker(CountDownLatch startLatch ,CountDownLatch doneLatch,int taskId){
            this.startLatch = startLatch;
            this.doneLatch = doneLatch;
            this.taskId = taskId;
        }
        @Override
        public void run() {
            try {
                startLatch.await();
                System.out.println("任务-"+taskId+"开始执行");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("任务-"+taskId+"执行完毕");
                doneLatch.countDown();
            }
        }
    }
}
