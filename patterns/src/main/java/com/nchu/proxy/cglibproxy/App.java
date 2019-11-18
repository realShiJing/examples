package com.nchu.proxy.cglibproxy;

import org.junit.Test;

/**
 * @Decription Cglib 代理测试
 * @Author yangsj
 * @Date 20191118 14:10
 **/
public class App {


    @Test
    public void test(){
        //创建目标对象
        RealSubject subject = new RealSubject();
        //获取代理对象
        RealSubject proxyInstance =(RealSubject) new MyMethodInterceptor(subject).getProxyInstance();
        //执行代理对象的方法
        proxyInstance.doOperation();
    }
}
