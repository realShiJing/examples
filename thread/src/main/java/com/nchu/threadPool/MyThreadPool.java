package com.nchu.threadPool;

import java.util.concurrent.BlockingQueue;

/**
 * @Decription 自定义线程池
 *  简化的线程池，仅用来说明工作原理
 * @Author yangsj
 * @Date 2020/2/28 14:29
 **/
public class MyThreadPool {
    // 利用阻塞队列实现生产者-消费者模式
    BlockingQueue<Runnable> workQue;
    // 保存内部工作线程
    // List<WorkerThread> threads = new ArrayList<>();
    // 构造方法
    MyThreadPool(int poolSize ,BlockingQueue workQue){
        this.workQue = workQue;
        // 创建工作线程
        for (int i = 0; i < poolSize; i++) {
            WorkerThread worker = new WorkerThread();
            worker.start();
            // threads.add(worker);
        }
    }
    // 提交任务
    void execute(Runnable runnable){
        try {
            workQue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 工作线程负责消费任务，并执行任务
    class WorkerThread extends Thread{
        @Override
        public void run() {
            try {
                //循环取任务并执行
                while(true){
                    Runnable task = workQue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
