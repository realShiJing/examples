package com.nchu.factory.abstraction;

/**
 * @Decription 抽象工厂角色，根据产品结构定义操作
 * @Author yangsj
 * @Date 20191022 11:43
 **/
public interface FruitFactory {

    /**
     * @Description 创建苹果对象
     * @Author yangsj
     * @Date 2019/10/22 11:45
     **/
    Apple getApple();

    /**
     * @Description 创建香蕉对象
     * @Author yangsj
     * @Date 2019/10/22 11:45
     **/
    Banana getBanan();
}
