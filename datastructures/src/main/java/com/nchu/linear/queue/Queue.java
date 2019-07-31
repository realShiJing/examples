package com.nchu.linear.queue;

/**
 * @Decription TODO
 * @Author yangshijing
 * @Date 20190729 14:31
 **/
public interface Queue {

    /**
     * @Description 判断队列是否满
     * @Author yangsj
     * @Date 2019/7/29 14:40
     **/
    boolean isFull();

    /**
     * @Description 判断队列是否为空
     * @Author yangsj
     * @Date 2019/7/29 14:40
     **/
    boolean isEmpty();

    /**
     * @Description 向队列中添加元素
     * @Author yangsj
     * @Date 2019/7/29 14:40
     **/
    void addQueue(int n);


    /**
     * @Description 从队列中获取元素
     * @Author yangsj
     * @Date 2019/7/29 14:40
     **/
    int getQueue();

    /**
     * @Description 显示队列中的元素
     * @Author yangsj
     * @Date 2019/7/29 14:40
     **/
    void showQueue();


    /**
     * @Description 显示队列的头部元素
     * @Author yangsj
     * @Date 2019/7/29 14:41
     **/
    void showHead();

}
