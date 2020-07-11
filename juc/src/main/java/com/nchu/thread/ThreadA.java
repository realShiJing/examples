package com.nchu.thread;

public class ThreadA extends Thread {
    private static Object obj = new Object();
    public ThreadA(String name){
        super(name);
    }

    public void run() {
        synchronized (obj) {
            try {
                // 打印输出结果
                System.out.println(Thread.currentThread().getName() + " wait");

                // 唤醒当前的wait线程
                obj.wait();

                // 打印输出结果
                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
