package com.nchu.factory.abstraction;

import org.junit.Test;

/**
 * @Decription 客户端测试
 * @Author yangsj
 * @Date 20191022 11:51
 **/
public class App {

    /**
     * @Description 抽象工厂测试
     * @Author yangsj
     * @Date 2019/10/22 11:52
     **/
    @Test
    public void test1(){
        //由北方水果工厂创建水果
        FruitFactory northFruitFactory = new NorthFruitFactory();

        Apple northApple = northFruitFactory.getApple();
        northApple.get();

        Banana northBanana = northFruitFactory.getBanan();
        northBanana.get();
        //由南方水果工厂创建水果
        FruitFactory southFruitFactory = new SouthFruitFactory();

        Apple southApple = southFruitFactory.getApple();
        southApple.get();

        Banana southBanana = southFruitFactory.getBanan();
        southBanana.get();
    }

}
