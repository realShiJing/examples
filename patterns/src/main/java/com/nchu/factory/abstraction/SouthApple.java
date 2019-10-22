package com.nchu.factory.abstraction;

/**
 * @Decription  具体产品：南方苹果(产品族)
 * @Author yangsj
 * @Date 20191022 11:35
 **/
public class SouthApple extends Apple{
    /**
     * @Description 实现具体操作
     * @Author yangsj
     * @Date 2019/10/22 11:33
     **/
    @Override
    public void get() {
        System.out.println("采集南方苹果");
    }
}
