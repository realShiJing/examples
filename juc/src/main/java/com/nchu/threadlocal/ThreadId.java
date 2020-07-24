package com.nchu.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Decription 线程局部变量
 * @Author yangsj
 * @Date 2020/2/7 9:22 下午
 **/

public class ThreadId {
    // 原子性整数，包含下一个分配的线程Thread ID
    static final AtomicLong  nextId=new AtomicLong(0);
    //定义ThreadLocal变量,每一个线程对应的Thread ID
    static final ThreadLocal<Long> tl = ThreadLocal.withInitial(()->nextId.getAndIncrement());
    //此方法会为每个线程分配一个唯一的Id
    static long get(){
        return tl.get();
    }
}
