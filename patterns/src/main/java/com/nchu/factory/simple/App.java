package com.nchu.factory.simple;

/**
 * @Decription 测试
 * @Author yangsj
 * @Date 20191018 9:34
 **/
public class App {

    /**
     * @Description 获取各种水果
     * @Author yangsj
     * @Date 2019/10/18 9:35
     **/
    public static void main(String[] args){
        Fruit apple = FruitFactory.getFruit("Apple");
        Fruit banana = FruitFactory.getFruit("Banana");
        apple.get();
        banana.get();
    }
}
