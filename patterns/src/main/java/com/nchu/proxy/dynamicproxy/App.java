package com.nchu.proxy.dynamicproxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Decription 动态代理测试
 * @Author yangsj
 * @Date 2019-11-17 14:39
 **/
public class App {
    @Test
    public void test(){
        //目标对象
        Subject realSubject = new RealSbuject();
        //代理处理器，将目标对象传入
        ProxyHandler proxyHandler = new ProxyHandler(realSubject);
        //动态生成代理对象
        Subject subject = (Subject)Proxy.newProxyInstance(RealSbuject.class.getClassLoader(), RealSbuject.class.getInterfaces(), proxyHandler);
        //调用被代理后的处理方法
        subject.doOperation();

    }
}
