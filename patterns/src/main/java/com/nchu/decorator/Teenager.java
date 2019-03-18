package com.nchu.decorator;

/**
 * @description: 具体组件角色：为抽象组件的实现类。
 * @auther: yangsj
 * @created: 2019/3/18 16:57
 */
public class Teenager extends Person {
    public Teenager() {
        description = "Shopping List:";
    }
    @Override
    public double cost() {
        return 0;
    }
}
