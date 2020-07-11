package com.nchu.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @Decription 简化的线程池，仅用来说明工作原理
 * @Author yangsj
 * @Date 2020/2/3 7:19 下午
 **/
public class ThreadPool {
    //利用阻塞队列实现生产者-消费者模式
    BlockingQueue<Runnable> workQueue;
    //保存内部工作线程
    List<WorkerThread> threads = new ArrayList<>();
    // 构造方法
    ThreadPool(int poolSize,BlockingQueue<Runnable> workQueue){
        this.workQueue = workQueue;
        // 创建工作线程
        for(int idx=0; idx<poolSize; idx++){
            WorkerThread work = new WorkerThread();
            work.start();
            threads.add(work);
        }
    }
    // 提交任务
    void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }
    // 工作线程负责消费任务，并执行任务
    class WorkerThread extends Thread{
        public void run() {
            //循环取任务并执行
            while(true){ // ①
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }
}
