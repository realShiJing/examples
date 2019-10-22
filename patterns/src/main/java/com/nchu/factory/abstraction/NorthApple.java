package com.nchu.factory.abstraction;

/**
 * @Decription 具体产品：北方苹果(产品族)
 * @Author yangjs
 * @Date 20191022 11:31
 **/
public class NorthApple extends Apple {

    /**
     * @Description 实现具体操作
     * @Author yangsj
     * @Date 2019/10/22 11:33
     **/
    @Override
    public void get() {
        System.out.println("采集北方苹果");
    }
}
