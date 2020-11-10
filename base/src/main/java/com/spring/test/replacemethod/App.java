package com.spring.test.replacemethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription replace-method 测试
 * @Author yangsj
 * @Date 2020/11/10 11:40 上午
 **/
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/org/springframework/bean/application.xml");
        Method method = (Method) context.getBean("method");
        method.display();
    }
}
