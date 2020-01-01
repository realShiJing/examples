package com.nchu.adapter;

/**
 * @Decription 对象适配器：通过组合目标源，获取目标源的功能
 * @Author yangsj
 * @Date 2020-01-01 23:32
 **/
public class Adapter2 implements Target {
    /**
     * 目标源
     */
    private Adaptee adaptee;

    /**
     * 构造方法，在适配器初始化的时候，传入目标源
     */
    Adapter2(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    /**
     * 调用目标源方法
     */
    @Override
    public void handle() {
        adaptee.request();
    }
}
