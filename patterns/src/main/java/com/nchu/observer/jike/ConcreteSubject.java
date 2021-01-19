package com.nchu.observer.jike;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ConcreteSubject 具体被观察者 ，内部包含所有注册的观察者
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 14:23
 **/
public class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
