package com.nchu.semaphore;

import org.junit.Test;

/**
 * @Decription Semaphore 相关测试
 * @Author yangsj
 * @Date 2020/1/16 15:24
 **/
public class App {

    /**
     * @Description 创建对象池
     * @Author yangsj
     * @Date 2020/1/16 15:25
     **/
    @Test
    public void test() throws InterruptedException {
        // 初始化对象池限流器
        ObjPool<Long, String> pool = new ObjPool(10, 2L);
        // 多线程通过对象池获取 t ，只允许同时十个线程对其操作
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(()->{
                try {
                    pool.exec(t->{
                        return t.toString();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
           thread.start();
        }
        Thread.sleep(100000);
    }
}
