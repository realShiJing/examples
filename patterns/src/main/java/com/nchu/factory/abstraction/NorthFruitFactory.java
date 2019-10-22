package com.nchu.factory.abstraction;

/**
 * @Decription 北方水果工厂，用于创建北方水果（产品结构）
 * @Author yangsj
 * @Date 20191022 11:46
 **/
public class NorthFruitFactory implements FruitFactory {


    /**
     * @Description 创建北方苹果（产品族）
     * @Author yangsj
     * @Date 2019/10/22 11:50
     **/
    @Override
    public Apple getApple() {
        return new NorthApple() ;
    }


    /**
     * @Description 创建北方香蕉（产品族）
     * @Author yangsj
     * @Date 2019/10/22 11:50
     **/
    @Override
    public Banana getBanan() {
        return new NorthBanan();
    }
}
