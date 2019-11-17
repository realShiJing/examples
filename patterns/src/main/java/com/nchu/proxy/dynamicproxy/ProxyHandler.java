package com.nchu.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Decription 创建实现InvocationHandler接口的代理处理器
 * @Author yangsj
 * @Date 2019-11-17 14:33
 **/
public class ProxyHandler implements InvocationHandler {
    //用于接收具体实现类的实例对象
    private  Object object;

    //使用带参数的构造器来传递具体实现类的对象
    public ProxyHandler(Object object) {
        this.object = object;
    }
    /** 重写 InvocationHandler 接口的 invoke 方法，在此方法中对代理的对象进行额外处理
     * @Description
     * @Author yangsj
     * @Date 2019-11-17 14:37
     **/
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("动态代理前置内容");
        method.invoke(object,objects);
        System.out.println("动态代理后置内容");
        return null;
    }
}
