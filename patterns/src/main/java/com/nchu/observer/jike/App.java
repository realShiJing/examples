package com.nchu.observer.jike;

/**
 * @ClassName: App
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 14:29
 **/
public class App {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers(new Message());
    }
}
