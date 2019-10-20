package com.nchu.factory.method;

/**
 * @Decription 具体工厂角色
 * @Author yangsj
 * @Date 2019-10-20 11:18
 **/
public class BananaFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
