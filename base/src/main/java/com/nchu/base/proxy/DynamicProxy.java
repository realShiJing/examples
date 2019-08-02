package com.nchu.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class DynamicProxy {
    public static void main(String[] args) {
        User user = new UserImp();
        InvocationHandler h = new DynamicProxyHandler(user);
        //User proxy = (User) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),user.getClass().getInterfaces(),h );
        User proxy = (User) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{User.class}, h);
        proxy.eat("苹果");
    }
}

interface User {
    void eat(String s);
}

class UserImp implements User {
    @Override
    public void eat(String s) {
        System.out.println("我要吃" + s);
    }
}

/**
 * 创建实现InvocationHandler接口的代理处理器
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object object;//用于接收具体实现类的实例对象

    //使用带参数的构造器来传递具体实现类的对象
    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理前置内容");
        method.invoke(object, args);
        System.out.println("动态代理后置内容");
        return null;
    }
}