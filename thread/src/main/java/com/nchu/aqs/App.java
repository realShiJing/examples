package com.nchu.aqs;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/1/13 14:08
 **/
public class App {
    public ReentrantLock lock = new ReentrantLock();


    /**
     * @Description
     * @Author yangsj
     * @Date 2020/1/13 14:09
     **/
    @Test
    public void test(){
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(()->{
                lock.lock();
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
}
