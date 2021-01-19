package com.nchu.observer.jike;

/**
 * @ClassName: ConcreteObserverTwo 具体观察者
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 14:28
 **/
public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverTwo is notified.");
    }
}
