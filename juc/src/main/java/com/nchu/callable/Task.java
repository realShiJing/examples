package com.nchu.callable;

import java.util.concurrent.Callable;

/**
 * @Decription  自定义Callable
 * @Author yangsj
 * @Date 2020/1/6 16:35
 **/
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 模拟计算需要一秒
        Thread.sleep(1000);
        return 1;
    }
}
