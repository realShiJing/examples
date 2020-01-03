package com.nchu.adapter;

/**
 * @Decription 类适配器:继承目标源的功能
 * @Author yangsj
 * @Date 2020-01-01 23:25
 **/
public class Adapter extends Adaptee implements Target {
    /**
     * @Description  执行目标源方法
     * @Author yangsj
     * @Date 2020-01-01 23:35
     **/
    @Override
    public void handle() {
        super.request();
    }
}
