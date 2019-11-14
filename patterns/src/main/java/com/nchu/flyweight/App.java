package com.nchu.flyweight;

import org.junit.Test;

/**
 * @Decription 享元模式测试
 * @Author yangsj
 * @Date 20191114 11:14
 **/
public class App {
    @Test
    public void test(){
        FlyWeightFacotry facotry = new FlyWeightFacotry();
        FlyWeight flyWeight = facotry.getFlyweight("one");
        flyWeight.doOperation("");

        flyWeight = facotry.getFlyweight("two");
        flyWeight.doOperation("");

        flyWeight = facotry.getFlyweight("three");
        flyWeight.doOperation("first");


        FlyWeight flyWeight2 = facotry.getFlyweight("three");
        //测试通过相同的 key 从享元工厂中获取的对象是否相同
        System.out.println(flyWeight == flyWeight2);
        //测试相同对象的不同状态
        flyWeight2.doOperation("second");
    }
}
