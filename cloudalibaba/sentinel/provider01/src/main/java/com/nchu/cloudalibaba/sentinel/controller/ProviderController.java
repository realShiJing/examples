package com.nchu.cloudalibaba.sentinel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/22 11:44
 **/
@RestController
public class ProviderController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/provider/getInfo/{id}")
    public String getInfo(@PathVariable("id")Long id){
        return "Hello Sentinel " + serverPort;
    }
}
