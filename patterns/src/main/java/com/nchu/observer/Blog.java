package com.nchu.observer;

import java.util.Observable;

/**
 * @description: 博客发布，被观察者
 * @auther: yangsj
 * @created: 2019/3/21 17:25
 */
public class Blog extends Observable {

    public void publishArtical(){
        Artical artical = new Artical();
        artical.setTitle("title");
        artical.setCoutent("countent");
        //设置被观察者的状态已更新
        this.setChanged();
        //通知所有观察者，并发送消息
        this.notifyObservers(artical);
    }
}
