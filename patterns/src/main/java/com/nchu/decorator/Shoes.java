package com.nchu.decorator;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/18 17:02
 */
public class Shoes extends ClothingDecorator {

    Person person;

    public  Shoes(Person person){
        this.person = person;
    }

    @Override
    public String getDescription() {
        return person.getDescription() + " a shoes" ;
    }

    @Override
    public double cost() {
        return 120 + person.cost();
    }
}
