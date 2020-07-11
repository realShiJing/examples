package com.nchu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Decription 四个线程循环打印 ABCD
 * @Author yangsj
 * @Date 2020/3/26 10:49 下午
 **/
public class ThreadOrderWithCondition {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition C_A = LOCK.newCondition();
    private static final Condition C_B = LOCK.newCondition();
    private static final Condition C_C = LOCK.newCondition();

    /**
     * init for A to run first
     */
    private volatile int flag = 'A';

    Runnable a = () -> {
        while (true) {
            LOCK.lock();

            if (flag == 'A') {
                System.out.println("A");
                flag = 'B';
                // signal B to run
                C_B.signal();
            } else {
                try {
                    // block and wait signal to invoke
                    C_A.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            LOCK.unlock();
        }
    };

    Runnable b = () -> {
        while (true) {
            LOCK.lock();

            if (flag == 'B') {
                System.out.println("B");
                flag = 'C';
                // signal C to run
                C_C.signal();
            } else {
                try {
                    // block and wait signal to invoke
                    C_B.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            LOCK.unlock();
        }
    };

    Runnable c = () -> {
        while (true) {
            LOCK.lock();

            if (flag == 'C') {
                System.out.println("C");
                flag = 'A';
                // signal A to run
                C_A.signal();
            } else {
                try {
                    // block and wait signal to invoke
                    C_C.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            LOCK.unlock();
        }
    };

    public void runTest() {
        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        Thread threadC = new Thread(c);

        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void main(String[] args) {
        ThreadOrderWithCondition o = new ThreadOrderWithCondition();
        o.runTest();
    }
}
