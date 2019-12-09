package com.nchu.bridge;

/**
 * @Decription Abstraction：抽象类
 * @Author yangsj
 * @Date 20191209 15:08
 **/
public abstract class Car {

    /**
     * 组合发动机
     */
    public Engine engine;

    Car(Engine engine){
        this.engine = engine;
    }

    /**
     * 发动汽车(抽象方法，具体实现交给子类)
     */
    public abstract void start() ;
}
