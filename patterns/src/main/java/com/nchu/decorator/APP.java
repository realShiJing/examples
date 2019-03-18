package com.nchu.decorator;


import org.junit.Test;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/18 17:04
 */
public class APP {
    @Test
    public void  Test(){
        Person person = new Teenager();
        person = new Shirt(person);
        person = new Shoes(person);
        System.out.println(person.getDescription() + person.cost() );
    }
}
