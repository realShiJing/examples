package com.nchu.base.proxy;

/**
 * 总接口
 */
interface Iuser {
    void eat(String s);
}

/**
 * 具体实现类
 */
class UserImpl implements Iuser {
    @Override
    public void eat(String s) {
        System.out.println("我要吃" + s);
    }
}

/**
 * 代理类
 */
class UserProxy implements Iuser {
    private Iuser user = new UserImpl();

    @Override
    public void eat(String s) {
        System.out.println("静态代理前置内容");
        user.eat(s);
        System.out.println("静态代理后置内容");
    }
}

/**
 * 静态代理
 */
public class StaticProxy {
    public static void main(String[] args) {
        UserProxy proxy = new UserProxy();
        proxy.eat("苹果");
    }
}

