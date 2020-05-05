package com.example.dubbo.boot.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.nchu.dubbo.DemoService;
import org.springframework.stereotype.Component;


/**
 * @Decription 服务提供方实现接口
 * @Author yangsj
 * @Date 2020/5/5 12:51 上午
 **/
@Service(version = "1.0.0",timeout = 2000)//dubbo注解 暴露服务
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello version:1.0.0"+name;
    }
}
