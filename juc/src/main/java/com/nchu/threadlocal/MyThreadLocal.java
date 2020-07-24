package com.nchu.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Decription 自定义 ThreadLocal
 * 这种方案容易产生内存泄露。在我们的设计方案中，ThreadLocal 持有的 Map 会持有 Thread 对象的引用，
 * 这就意味着，只要 ThreadLocal 对象存在，那么 Map 中的 Thread 对象就永远不会被回收。
 * ThreadLocal 的生命周期往往都比线程要长，所以这种设计方案很容易导致内存泄露
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