package com.nchu.volatiletest;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @Decription volatile关键字的相关测试
 * @Author yangsj
 * @Date 20191016 16:00
 **/
public class VolatileTest {
   // public  AtomicInteger inc = new AtomicInteger(0);

    public /*volatile*/ int inc = 0 ;

    public void increase() {
        // 多线程环境下无法保证 i++ 的原子性
        inc++;
        //inc.getAndIncrement();
    }
    /**
     * @Description  可见性测试
     * @Author yangsj
     * @Date `2020/2/10` 5:14 下午
     **/
    @Test
    public void test(){
        VolatileTest test = new VolatileTest();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+":"+test.inc);
            // 让子线程睡一秒很关键，此时主线程已经在循环判断，如果睡眠结束后，共享变量的值被修改，但是主线程还在循环，证明主线程对子线程的修改不可见
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.increase();
            System.out.println(Thread.currentThread().getName()+":"+test.inc);
        }).start();

        while(test.inc==0){
            //System.out.println("主线程不可见子线程对共享变量的修改！"+test.inc);
        }
        System.out.println("主线程可见子线程对共享变量的修改！"+test.inc);

    }
    /**
     * @Description 测试结果，每次打印的的值都不一样，
     * 证明volatile修饰的变量在多线程的环境下无法保持原子性
     * 解决方案：用原子类
     * @Author yangsj
     * @Date 2019/10/17 15:37
     **/
    public static void main(String[] args) throws InterruptedException {
        final VolatileTest test = new VolatileTest();
        CountDownLatch latch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<100000;j++){
                    test.increase();
                }
                latch.countDown();
            }).start();

        }
        latch.await();

        System.out.println(test.inc);
    }


    int a = 1 ;
    /*volatile*/ boolean flag = false;
    int ret = 0 ;
    public void write(){
        a = 2;      //1
        flag = true;//2
    }

    public void multiply(){
        if(flag){         //3
            ret = a*a;    //4
        }
    }


    /**
     * @Description 有序性测试
     * 步骤1和步骤2 在变量 flag 没有 volatile 修饰的情况下有可能发生指令重排
     * @Author yangsj
     * @Date 2019/10/18 13:56
     **/
    @Test
    public void OrderlyTest(){
        for (int i = 0; i < 1000000 ; i++) {
            VolatileTest test = new VolatileTest();
            //CountDownLatch latch = new CountDownLatch(1);
            //线程A执行对数据和flag状态的改变
            new Thread(() -> {
                test.write();
                //latch.countDown();
            }).start();
            //线程B判断flag状态，并处理数据
            new Thread(() -> {
                test.multiply();
                //latch.countDown();
            }).start();
            /*try {
                //等待两个线程执行完毕
                //latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            if(test.ret == 1){
                System.out.println(test.ret);
            }
        }
    }
}
