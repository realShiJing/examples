package com.nchu.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Decription Semaphore 场景分析
 * @Author yangsj
 * @Date 2020/7/22 11:20
 **/
public class SemaphoreTest01 {
    public static void main(String[] args) throws InterruptedException {
        // 创建值为2的通行证,公平锁模式，有线程入队后，即使剩余通行证可用，后续线程也许入队
        // 非公平锁模式下，如果还有剩余通行证，就可以获取
        Semaphore semaphore = new Semaphore(2,true);


        Thread threadA = new Thread(()->{
            try {
                //申请一个通行证
                System.out.println("线程A申请一个通行证");
                semaphore.acquire();
                System.out.println("线程A申请到一个通行证");
                // 持有通行证 10 秒
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println("线程A释放一个通行证");
            }
        });
        threadA.start();
        //睡眠一秒等待线程A获取到令牌
        TimeUnit.SECONDS.sleep(1);

        // 此时线程A仍然持有令牌
        Thread threadB = new Thread(()->{
            try {
                //申请两个通行证
                System.out.println("线程B申请两个通行证");
                semaphore.acquire(2);
                System.out.println("线程B申请到两个通行证");
                // 持有通行证 10 秒
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(2);
                System.out.println("线程B释放两个通行证");

            }
        });
        threadB.start();

        TimeUnit.SECONDS.sleep(1);

        Thread threadC = new Thread(()->{
            try {
                //申请一个通行证
                System.out.println("线程C申请一个通行证");
                semaphore.acquire();
                System.out.println("线程C申请到一个通行证");
                // 持有通行证 100 秒
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println("线程C释放一个通行证");
            }
        });
        threadC.start();
    }
}
