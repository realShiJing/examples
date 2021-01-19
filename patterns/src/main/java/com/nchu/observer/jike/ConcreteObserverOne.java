package com.nchu.observer.jike;

/**
 * @ClassName: ConcreteObserverOne 具体观察者
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 14:28
 **/
public class ConcreteObserverOne implements Observer {
    @Override
    public void update(Message message) {
        //获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverOne is notified.");
    }
}
