package com.nchu.interrupt;

import org.junit.Test;

/**
 * @Decription 测试线程中断相关
 * @Author yangsj
 * @Date 2020/1/8 14:23
 **/
public class InterruptTest {

    /**
     * @Description 在 NEW 和 TERMINATED状态下调用中断方法来中断线程的时候，Java认为毫无意义，所以并不会设置线程的中断标识位。
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testNEW(){
        Thread thread = new Thread();
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    /**
     * @Description 处于NEW和TERMINATED状态的线程，对于中断是屏蔽的，也就是说中断操作对这两种状态下的线程是无效的
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testTERMINATED() throws InterruptedException {
        Thread thread = new Thread();
        // 开启线程
        thread.start();
        // 等待线程结束
        thread.join();
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    /**
     * @Description 处于 RUNNABLE 状态的线程，当中断线程后，会修改其中断标志位，但并不会影响线程本身
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testRUNNABLE() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                // 什么都不做，就是空转
            }
        });
        // 开启线程
        thread.start();
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        Thread.sleep(5000);
        System.out.println(thread.getState());
    }

    /**
     * @Description 处于 RUNNABLE 状态的线程，当中断线程后，会修改其中断标志位，给了我们程序更大的灵活性去判断和处理中断
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testRUNNABLE1() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                // 如果当前线程被标记为 中断状态 ，则退出循环,当前线程执行结束，状态变为 TERMINATED
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("exit MyThread!");
                    break;
                }
            }
        });
        // 开启线程
        thread.start();
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        Thread.sleep(1000);
        System.out.println(thread.getState());
    }

    /**
     * @Description 处于  WAITING 状态的线程，当中断线程后，会抛出异常并清空中断标志位。
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testWAITING() throws InterruptedException {
        Thread thread = new Thread(()->{
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    // 在该线程处于 WAITING 状态时，中断该线程，会抛出 InterruptedException异常，该线程的中断标志位被清空
                    System.out.println("catch InterruptedException");
                }
            }
        });
        // 开启线程
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());
    }

    /**
     * @Description 处于 TIMED_WAITING 状态的线程，当中断线程后，会抛出异常并清空中断标志位。
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testSLEEP() throws InterruptedException {
        Thread thread = new Thread(()->{
            synchronized (this){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 在该线程处于 TIMED_WAITING 状态时，中断该线程，会抛出 InterruptedException异常，该线程的中断标志位被清空
                    System.out.println("catch InterruptedException");
                }
            }
        });
        // 开启线程
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.getState());
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());
    }

    /**
     * @Description interrupted()测试的是当前的线程的中断状态。
     * 而isInterrupted()测试的是调用该方法的对象所表示的线程。
     * 一个是静态方法（它测试的是当前线程的中断状态），
     * 一个是实例方法（它测试的是实例对象所表示的线程的中断状态）。
     * 一个会清除中断标识位，另一个不会清除中断标识位。
     *
     * @Author yangsj
     * @Date 2020/1/8 14:26
     **/
    @Test
    public void testInterrupted() throws InterruptedException {
        Thread thread = new Thread(()->{
            synchronized (this){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 在该线程处于 TIMED_WAITING 状态时，中断该线程，会抛出 InterruptedException异常，该线程的中断标志位被清空
                    System.out.println("catch InterruptedException");
                }
            }
        });
        // 开启线程
        thread.start();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println(Thread.interrupted());
    }
}
