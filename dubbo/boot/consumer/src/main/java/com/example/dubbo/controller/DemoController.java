package com.example.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nchu.dubbo.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/5/5 1:13 上午
 **/
@RestController
public class DemoController {

    //使用自定义负载均衡器
    @Reference(version = "2.0.0", loadbalance = "onlyFirst")
    DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return demoService.sayHello("yansj");
    }
}
