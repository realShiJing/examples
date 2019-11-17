package com.nchu.proxy.staticproxy;

/**
 * @Decription 具体实现类
 * @Author yangsj
 * @Date 2019-11-17 14:22
 **/
public class RealSubject implements Subject{
    @Override
    public void doOperation() {
        System.out.println("方法执行");
    }
}
