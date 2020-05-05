package com.nchu.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Decription 服务消费者
 * @Author yangsj
 * @Date 2020/5/4 10:40 下午
 **/
public class Consumer {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService");
        //远程调用服务方提供的服务
        String sayHello = demoService.sayHello("yangsj");
        System.out.println(sayHello);
        System.in.read();
    }
}
