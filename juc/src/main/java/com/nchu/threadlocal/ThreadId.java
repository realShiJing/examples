package com.nchu.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/2/7 9:22 下午
 **/

public class ThreadId {
    static final AtomicLong
            nextId=new AtomicLong(0);
    //定义ThreadLocal变量
    static final ThreadLocal<Long> tl = ThreadLocal.withInitial(
            ()->nextId.getAndIncrement());
    //此方法会为每个线程分配一个唯一的Id
    static long get(){
        return tl.get();
    }
}
