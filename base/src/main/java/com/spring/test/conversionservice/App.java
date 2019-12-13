package com.spring.test.conversionservice;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription 测试 自定义类型 转换器
 * @Author yangsj
 * @Date 2019/12/11 10:59
 **/
public class App {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/org/springframework/bean/conversion.xml");
        StudentService studentService = (StudentService) context.getBean("studentService");
        System.out.println(studentService);
    }
}
