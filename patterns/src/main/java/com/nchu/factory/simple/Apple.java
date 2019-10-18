package com.nchu.factory.simple;

/**
 * @Decription 具体产品角色
 * @Author yangsj
 * @Date 20191018 9:17
 **/
public class Apple implements Fruit{

    /**
     * @Description 采集苹果
     * @Author yangsj
     * @Date 2019/10/18 9:18
     **/
    @Override
    public void get() {
        System.out.println("采集苹果");
    }
}
