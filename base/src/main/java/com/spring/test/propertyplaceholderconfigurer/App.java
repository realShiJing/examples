package com.spring.test.propertyplaceholderconfigurer;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/11 10:30
 **/
public class App {
    @Test
    public void test(){
        ApplicationContext  context = new ClassPathXmlApplicationContext("/org/springframework/bean/application.xml");
        TestBean testBean = (TestBean) context.getBean("testBean");
        System.out.println("testBean name "+ testBean.getName());
    }
}
