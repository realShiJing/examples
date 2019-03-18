package com.nchu.decorator;

/**
 * @description: 抽象组件角色： 一个抽象接口，是被装饰类和装饰类的父接口。
 * @auther: yangsj
 * @created: 2019/3/18 16:55
 */
public abstract  class Person {
    String description = "Unkonwn";
    //物品描述
    public String getDescription()
    {
        return description;
    }
    //计算花费
    public abstract double cost();
}