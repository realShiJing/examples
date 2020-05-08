package com.example.cloud.eureka.controller;

import com.nchu.cloud.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 15:44
 **/
@RestController
public class ConsumerController {

    public static final String PROVIDER_URL = "http://127.0.0.1:8002";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/provider/getInfo/{id}")
    public ResultVo getInfo(@PathVariable("id")String id){
        return restTemplate.getForObject(PROVIDER_URL + "/provider/getInfo/" + id, ResultVo.class);
    }
}
