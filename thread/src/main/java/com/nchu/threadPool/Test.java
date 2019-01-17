package com.nchu.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        for(int i=0;i<15;i++){
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("正在执行task "+index);
                    try {
                        Thread.currentThread().sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("task "+index+"执行完毕");
                }
            });
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
