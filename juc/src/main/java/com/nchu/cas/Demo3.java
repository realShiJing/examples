package com.nchu.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Decription 对 Demo2 进行优化，降低锁粒度，减少程序耗时
 * Q:如何减少耗时？
 * cout++分为三步操作：
 *  1.获取count值
 *  2.count + 1
 *  3.最新值赋值个count
 * 我们可以只对第三部进行加锁操作，细化锁粒度
 * @Author yangsj
 * @Date 2020/7/28 10:38
 **/
public class Demo3 {
    // volatile 修饰保证，内存可见性
    private static volatile int  count;

    /**
     * @Description 加一操作
     * @Author yangsj
     * @Date 2020/7/28 11:30
     **/
    public static  void request() throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(5);
        int newCount;
        int expCount;
        do {
            //1.获取count值(期望值)
           expCount = getCount();
            //2.最新值
           newCount = expCount + 1;
           // 如果CAS失败，则重新获取最新值，再次循环进行设置，直至设置陈宫
        }while (!compareAndSwap(expCount,newCount));


    }

    /**
     * @Description 比较并交换（加锁）
     * @Author yangsj
     * @Date 2020/7/28 12:49
     **/
    public static synchronized boolean compareAndSwap(int expCount ,int newCount){
        // 如果期望值和内存值一致，则将最新值赋值给count
        if(expCount == getCount()){
            count = newCount;
            //并返回true
            return true;
        }
        // 如果期望值和内存值不相等，说明有线程已经更新了内存值，
        // 当前线程计算得到的最新值已经失效
        return false;
    }

    public static int getCount(){
        return count;
    }
    /**
     * @Description 模拟并发请求
     * @Author yangsj
     * @Date 2020/7/28 11:03
     **/
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        long begin = System.currentTimeMillis();
        // 模拟100个请求
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                try {


                    // 1 个请求做10次加法
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }).start();
        }
        // 等待所有线程执行完毕
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end - begin) + " count值："+ count);
    }
}
