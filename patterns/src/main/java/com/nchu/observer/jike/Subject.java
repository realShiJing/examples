package com.nchu.observer.jike;


/**
 * @ClassName: Subject 被观察者
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 14:22
 **/
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Message message);
}
