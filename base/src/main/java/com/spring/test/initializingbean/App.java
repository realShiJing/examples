package com.spring.test.initializingbean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 20191210 09:30
 **/
public class App {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("org/springframework/bean/bean.xml");

        InitializingBeanTest initializingBeanTest = (InitializingBeanTest) context.getBean("initializingBeanTest");
        System.out.println(initializingBeanTest.name);
    }
}
