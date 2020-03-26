package com.nchu.threadlocal;

import org.junit.Test;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/2/7 9:23 下午
 **/
public class App {
    @Test
    public void test(){
        Thread threadA = new Thread(()->{
            System.out.println(ThreadId.get());

            System.out.println(ThreadId.get());
        });

        Thread threadB = new Thread(() -> {
            System.out.println(ThreadId.get());
        });
        threadA.start();
        threadB.start();

    }
}
