package com.nchu.jvm.invoke;

import org.junit.Test;

/**
 * @Decription 静态分派(重载)
 * @Author yangsj
 * @Date 2020/8/11 16:04
 **/
public class StaticDispatch {
    abstract class Human{}

    class Man extends Human{}

    class Woman extends Human{}

    public void sayHello(Human guy){
        System.out.println("hello guy!");
    }

    public void sayHello(Man man){
        System.out.println("hello gentleman!");
    }

    public void sayHello(Woman woman){
        System.out.println("hello lady!");
    }

    @Test
    public void test() {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sr = new StaticDispatch();

        sr.sayHello(man);
        // sr.sayHello((Man)man);

        sr.sayHello(woman);
        // sr.sayHello((Woman) woman);
    }
}
