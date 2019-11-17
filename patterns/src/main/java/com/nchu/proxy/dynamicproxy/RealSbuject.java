package com.nchu.proxy.dynamicproxy;

/**
 * @Decription 目标对象
 * @Author yangsj
 * @Date 2019-11-17 14:31
 **/
public class RealSbuject implements Subject {
    @Override
    public void doOperation() {
        System.out.println("方法执行");
    }
}
