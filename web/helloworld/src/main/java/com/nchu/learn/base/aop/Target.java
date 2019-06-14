package com.nchu.learn.base.aop;

import org.springframework.stereotype.Component;

/**
 * 目标对象
 */
@Component
public class Target {
    public Integer test(String s){
        Integer i = 100 ;
        System.out.println("目标执行方法------>"+s);
        return i;
    }
}
