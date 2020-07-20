package com.nchu.countdownlatch;

import java.util.concurrent.*;

/**
 * @Decription 主线程等待子线程结束后执行
 * @Author yangsj
 * @Date 2020/7/20 9:15
 **/
public class CountDownLatchTest01 {
    public static void main(String[] args) throws InterruptedException {
        // 子线程个数
        int threadNum = 8 ;
        // 创建栅栏
        CountDownLatch downLatch = new CountDownLatch(threadNum);

        // 创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8,
                10,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                r -> new Thread(r,"thread-name"+r.hashCode()));

        for (int i = 0; i < threadNum; i++) {
            poolExecutor.execute(new Worker(downLatch,i));
        }
        long start = System.currentTimeMillis();
        System.out.println("主线程等待子线程");
        downLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("主线程等待时长"+(end - start));
    }

    /**
     * @Description 自定义执行任务
     * @Author yangsj
     * @Date 2020/7/20 9:25
     **/
    static class Worker implements Runnable {
        private CountDownLatch latch;

        // 任务ID
        private int taskId;
        Worker(CountDownLatch latch,int taskId){
            this.latch = latch;
            this.taskId = taskId;
        }
        @Override
        public void run() {
            try {
                System.out.println("任务-"+taskId+"执行中");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("任务-"+taskId+"执行完毕");
                latch.countDown();
            }
        }
    }
}
