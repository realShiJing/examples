package com.nchu.volatiletest;

import org.junit.Test;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/2/7 10:37 下午
 **/
public class App {
    /**
     * @Description  主线程无法打印
     * @Author yangsj
     * @Date 2020/2/7 10:45 下午
     **/
    @Test
    public void  test(){
        ThreadDemo rn = new ThreadDemo();
        Thread thread = new Thread(rn);
        thread.start();
        while (true) {
            if(rn.isFlag()){
                System.out.println("----------");
                break;
            }
        }
    }
}
