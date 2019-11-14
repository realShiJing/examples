package com.nchu.observer;

import org.junit.Test;

/**
 * @description: 观察者模式测试
 * @auther: yangsj
 * @created: 2019/3/21 17:30
 */
public class APP {
    @Test
    public void Test(){
        //博客，被观察者
        Blog blog = new Blog();
        //用户，观察者
        User user = new User();
        //向发布者中注册订阅者
        blog.addObserver(user);
        //被观察者状态更新，并通知观察者
        blog.publishArtical();
        //删除订阅者后，后续被观察者状态的更新不会通知该订阅者
        blog.deleteObserver(user);
        blog.publishArtical();
    }
}
