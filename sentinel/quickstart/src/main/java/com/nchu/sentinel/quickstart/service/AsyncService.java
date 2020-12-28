package com.nchu.sentinel.quickstart.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/12/28 15:10
 **/
@Service
public class AsyncService {
    //Async表示方法为异步调用
    @Async
    public void hello(){
        System.out.println("异步调用开始======");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步调用结束=====");
    }
}
