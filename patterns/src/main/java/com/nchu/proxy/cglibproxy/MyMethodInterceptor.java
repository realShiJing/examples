package com.nchu.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Decription 实现 MethodTnterceptor 接口（方法调用的中转站）
 * @Author yangsj
 * @Date 20191118 13:58
 **/
public class MyMethodInterceptor implements MethodInterceptor {
    //目标对象
    private Object target;
    //构造器，传入一个被代理的对象
    MyMethodInterceptor(Object target){
        this.target = target;
    }

    /**
     * @Description 重写 intercept 方法 ，在方法内实现对代理对象方法的改造
     * @Author yangsj
     * @Date 2019/11/18 14:08
     **/
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib代理前置内容");
        //传入目标对象和参数列表
        Object returnVal = method.invoke(target, objects);
        System.out.println("Cglib代理后置内容");
        //返回方法结果
        return returnVal;
    }

    /**
     * @Description 返回一个代理对象
     * @Author yangsj
     * @Date 2019/11/18 14:07
     **/
    public Object getProxyInstance(){
        //1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2. 设置被代理类
        enhancer.setSuperclass(target.getClass());
        //3. 设置回调接口
        enhancer.setCallback(this);
        //4. 创建子类对象，即代理对象
        return enhancer.create();

    }
}
