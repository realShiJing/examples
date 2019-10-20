package com.nchu.factory.method;

import com.nchu.factory.simple.FruitFactory;

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
        Fruit apple = new  AppleFactory().getFruit();
        Fruit banana = new BananaFactory().getFruit();
        apple.get();
        banana.get();
    }
}
