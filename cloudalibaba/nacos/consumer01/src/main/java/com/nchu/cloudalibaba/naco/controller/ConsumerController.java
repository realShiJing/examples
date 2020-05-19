package com.nchu.cloudalibaba.naco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Decription 服务消费者
 * @Author yangsj
 * @Date 2020/5/19 10:16 下午
 **/
@RestController
public class ConsumerController {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/consumer/provider/getInfo")
    public String getInfo(){
        return  restTemplate.getForObject(serverUrl +"/provider/getInfo/",String.class);
    }


}
