package com.nchu.adapter;

import org.junit.Test;

/**
 * @Decription 测试适配器模式
 * @Author yangsj
 * @Date 2020-01-01 23:29
 **/
public class App {

    /**
     * @Description 测试类适配器
     * @Author yangsj
     * @Date 2020-01-01 23:31
     **/
    @Test
    public void test(){
        Computer computer = new Computer();
        Adapter adapter = new Adapter();
        // 通过适配器访问目标源
        computer.net(adapter);
    }

    /**
     * @Description  测试对象适配器
     * @Author yangsj
     * @Date 2020-01-01 23:31
     **/
    @Test
    public void test1(){
        Computer computer = new Computer();
        Adaptee adaptee = new Adaptee();
        // 向适配器中传入目标源
        Adapter2 adapter2 = new Adapter2(adaptee);
        computer.net(adapter2);
    }
}
