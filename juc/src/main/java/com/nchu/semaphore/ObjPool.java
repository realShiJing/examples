package com.nchu.semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Decription Semaphore 实现限流器
 * @Author yangsj
 * @Date 2020/1/16 15:18
 **/
public class ObjPool<T,R> {
    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;
    // 构造函数,size表示限制线程进入的个数，T 代表共享对象
    ObjPool(int size, T t){
        // 创建本地缓存 list
        pool = new Vector<T>(){};
        for(int i=0; i<size; i++){
            // 使用 vector 保证原子性操作
            pool.add(t);
        }
        // 初始化信号量大小
        sem = new Semaphore(size);
    }
    // 利用对象池的对象，调用 func
    R exec(Function<T,R> func) throws InterruptedException {
        T t = null;
        System.out.println(sem);
        // 信号量计数减一
        sem.acquire();
        try {
            // 为每个线程分配了一个对象 t（这个分配工作是通过 pool.remove(0) 实现的）
            t = pool.remove(0);
            System.out.println(Thread.currentThread().getName()+":"+t);
            Thread.sleep(10000);
            return func.apply(t);
        } finally {
            // 释放对象（这个释放工作是通过 pool.add(t) 实现的）
            pool.add(t);
            // 信号量计数加一
            sem.release();
        }
    }
}
