package com.nchu.factory.abstraction;

/**
 * @Decription 具体产品：南方香蕉(产品族)
 * @Author yangsj
 * @Date 20191022 11:37
 **/
public class SouthBanan extends Banana {
    /**
     * @Description 实现具体操作
     * @Author yangsj
     * @Date 2019/10/22 11:33
     **/
    @Override
    public void get() {
        System.out.println("采集南方香蕉");
    }
}
