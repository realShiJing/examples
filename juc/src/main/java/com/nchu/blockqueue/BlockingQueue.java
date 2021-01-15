package com.nchu.blockqueue;


/**
 * @Description 自定义阻塞队列
 * @Author yangsj
 * @Date 2020/7/17 17:23
 **/
public interface BlockingQueue<T> {


    /**
     * @Description 向阻塞队列中阻塞的添加数据
     * @Author yangsj
     * @Date 2020/7/17 17:24
     **/
    void put(T t) throws InterruptedException;


    /**
     * @Description 从阻塞队列中阻塞的获取数据
     * @Author yangsj
     * @Date 2020/7/17 17:24
     **/
    T take() throws InterruptedException;

}
