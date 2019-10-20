package com.nchu.factory.method;

/**
 * @Description  抽象工厂角色
 * @Author yangsj
 * @Date 2019-10-20 11:15
 **/
public interface FruitFactory {
    /**
     * @Description  获取水果抽象方法
     * @Author yangsj
     * @Date 2019-10-20 11:16
     **/
    abstract Fruit getFruit();
}
