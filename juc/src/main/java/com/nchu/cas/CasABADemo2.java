package com.nchu.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Decription 版本号解决ABA 问题
 * @Author yangsj
 * @Date 2020/7/28 16:43
 **/
public class CasABADemo2 {
    // 带版本号的原子类
    private static AtomicStampedReference<Integer> a = new AtomicStampedReference(0, 0);

    public static void main(String[] args) {
        Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值：" + a.getReference());
                try {

                    int expectNum = a.getReference(); // 0
                    int newNum = expectNum + 1; // 1

                    int expectStemp = a.getStamp();
                    int newStemp = a.getStamp() + 1;
                    Thread.sleep(1000);//主线程休眠一秒钟，让出cpu

                    // 线程B 加一再减一，对线程A 来说，内存值和期望值是一致的，但是版本号不一致，所以cas失败
                    // 线程A能觉察到线程B的操作
                    boolean isCASSccuess = a.compareAndSet(expectNum, newNum, expectStemp, newStemp);
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

                    a.compareAndSet(a.getReference(), a.getReference() + 1, a.getStamp(), a.getStamp() + 1);//a + 1,a=1
                    System.out.println("操作线程" + Thread.currentThread().getName() + "，【increment】,值=" + a.getReference());
                    a.compareAndSet(a.getReference(), a.getReference() - 1, a.getStamp(), a.getStamp() + 1);//a - 1,a=0
                    System.out.println("操作线程" + Thread.currentThread().getName() + "，【decrement】,值=" + a.getReference());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "干扰线程");

        main.start();
        other.start();
    }
}