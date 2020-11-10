package com.spring.test.replacemethod;

import org.springframework.beans.factory.support.MethodReplacer;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/10 11:39 上午
 **/
public class MethodReplace implements MethodReplacer {

    @Override
    public Object reimplement(Object o, java.lang.reflect.Method method, Object[] objects) throws Throwable {
        System.out.println("我是替换方法");
        return null;
    }
}