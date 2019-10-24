package com.nchu.factory.abstraction;

/**
 * @Decription 南方水果工厂，用于创建南方水果（产品结构）
 * @Author yangsj
 * @Date 20191022 11:49
 **/
public class SouthFruitFactory implements FruitFactory {
    /**
     * @Description 创建南方苹果（产品族）
     * @Author yangsj
     * @Date 2019/10/22 11:50
     **/
    @Override
    public Apple getApple() {
        return new SouthApple();
    }
    /**
     * @Description 创建南方香蕉（产品族）
     * @Author yangsj
     * @Date 2019/10/22 11:50
     **/
    @Override
    public Banana getBanan() {
        return new SouthBanan();
    }
}
