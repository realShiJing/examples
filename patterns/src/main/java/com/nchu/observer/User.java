package com.nchu.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @description: 用户（观察者）
 * @auther: yangsj
 * @created: 2019/3/21 17:28
 */
public class User implements Observer {
    /**
     * @Description  同步被观察者的状态更新，并做进一步处理
     * @Author yangsj
     * @Date 2019-11-12 21:59
     **/
    @Override
    public void update(Observable o, Object arg) {
        Artical artical = (Artical)arg;
        System.out.println("title: "+artical.getTitle() + " ;countent: " + artical.getCoutent());
    }
}
