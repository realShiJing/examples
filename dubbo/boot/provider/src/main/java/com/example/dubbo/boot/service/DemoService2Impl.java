package com.example.dubbo.boot.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.nchu.dubbo.DemoService;


/**
 * @Decription 服务提供方实现接口
 * @Author yangsj
 * @Date 2020/5/5 12:51 上午
 **/
@Service(version = "2.0.0",timeout = 2000)//dubbo注解 暴露服务
public class DemoService2Impl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello version:2.0.0"+name;
    }
}
