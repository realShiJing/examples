package com.nchu.cloudalibaba.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/19 19:26
 **/
@RestController
public class ProviderController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/provider/getInfo/")
    public String getInfo(){
        return "nacos registry, serverPort: "+ serverPort;
    }
}
