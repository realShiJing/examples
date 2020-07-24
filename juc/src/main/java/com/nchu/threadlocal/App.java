package com.nchu.threadlocal;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Decription 线程本地存储测试
 * @Author yangsj
 * @Date 2020/2/7 9:23 下午
 **/
public class App {
    @Test
    public void test() throws InterruptedException {
        Thread threadA = new Thread(()->{
            System.out.println("threadA"+ThreadId.get());

            System.out.println("threadA"+ThreadId.get());
        });

        Thread threadB = new Thread(() -> {
            System.out.println("threadB"+ThreadId.get());
        });
        threadA.start();
        threadB.start();

        TimeUnit.SECONDS.sleep(10);
    }
}
