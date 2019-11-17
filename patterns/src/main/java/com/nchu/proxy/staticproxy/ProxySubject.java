package com.nchu.proxy.staticproxy;

/**
 * @Decription 代理对象 与具体实现类实现同一个接口
 * @Author yangsj
 * @Date 2019-11-17 14:26
 **/
public class ProxySubject implements Subject {
    public Subject reaalSubject = new RealSubject();
    @Override
    public void doOperation() {
        System.out.println("静态代理前置内容");
        reaalSubject.doOperation();
        System.out.println("静态代理后置内容");
    }
}
