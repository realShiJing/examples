package com.nchu.factory.abstraction;

/**
 * @Decription 产品结构 抽象角色
 * @Author yangsj
 * @Date 20191022 11:28
 **/
public abstract class Apple implements Fruit{


    /**
     * @Description 抽象方法，由子类（产品族）实现
     * @Author yangsj
     * @Date 2019/10/22 11:30
     **/
    public abstract void get();
}
