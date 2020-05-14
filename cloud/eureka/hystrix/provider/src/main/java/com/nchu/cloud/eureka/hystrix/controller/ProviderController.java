package com.nchu.cloud.eureka.hystrix.controller;

import com.nchu.cloud.eureka.hystrix.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 14:33
 **/
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/provider/getInfo/{id}")
    public String getInfo(@PathVariable("id") Integer id){
        return providerService.getInfo(id);
    }

    @GetMapping("/provider/getTimeOut/{id}")
    public String getTimeOut(@PathVariable("id") Integer id){
        return providerService.getTimeOut(id);
    }

    @GetMapping("/provider/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id){
        return providerService.circuitBreaker(id);
    }
}
