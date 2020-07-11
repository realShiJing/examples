package com.nchu.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Decription 自定义 ThreadLocal
 * @Author yangsj
 * @Date 2020/2/7 9:32 下午
 **/

class MyThreadLocal<T> {
    Map<Thread, T> locals =  new ConcurrentHashMap<>();
    //获取线程变量
    T get() {
        return locals.get(Thread.currentThread());
    }
    //设置线程变量
    void set(T t) {
        locals.put(Thread.currentThread(), t);
    }
}