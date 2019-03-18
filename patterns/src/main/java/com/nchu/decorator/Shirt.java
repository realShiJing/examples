package com.nchu.decorator;

/**
 * @description: 具体装饰角色：为抽象装饰角色的实现类。负责具体的装饰。
 * @auther: yangsj
 * @created: 2019/3/18 17:00
 */
public class Shirt extends ClothingDecorator {

    Person person;

    public Shirt(Person person){
        this.person = person;
    }

    @Override
    public String getDescription() {
        return person.getDescription() + " a shirt ";
    }

    @Override
    public double cost() {
        return 100 + person.cost();
    }
}
