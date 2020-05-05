package com.nchu.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Decription 服务提供方
 * @Author yangsj
 * @Date 2020/5/4 10:34 下午
 **/
public class Provider {
    public static void main(String[] args) throws IOException {
        //加载服务提供方配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        //启动应用
        context.start();
        //阻塞应用，为了在dubbo-admain里查看
        System.in.read();// 按任意键退出

    }
}
