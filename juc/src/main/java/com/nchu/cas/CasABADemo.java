package com.nchu.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Decription ABA 问题
 * @Author yangsj
 * @Date 2020/7/28 14:45
 **/
public class CasABADemo {

    // 原子类
    private static AtomicInteger a = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值：" + a.get());
                try {

                    int expectNum = a.get(); // 0
                    int newNum = expectNum + 1; // 1
                    Thread.sleep(1000);//主线程休眠一秒钟，让出cpu

                    // 线程B 加一再减一，对线程A 来说，内存值和期望值是一致的，所以cas成功
                    // 并没有觉察到线程B的操作
                    boolean isCASSccuess = a.compareAndSet(expectNum, newNum);
                    System.out.println("操作线程" + Thread.currentThread().getName() + "，CAS操作：" + isCASSccuess);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "主线程");

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);//确保Thread-main线程优先执行

                    a.incrementAndGet();//a + 1,a=1
                    System.out.println("操作线程" + Thread.currentThread().getName() + "，【increment】,值=" +a.get());
                    a.decrementAndGet();//a - 1,a=0
                    System.out.println("操作线程" + Thread.currentThread().getName() + "，【decrement】,值=" +a.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "干扰线程");

        main.start();
        other.start();
    }

}
