package com.example.cloud.zookeeper.controller;

import com.example.cloud.zookeeper.service.ProviderService;
import com.nchu.cloud.vo.ResultVo;
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
    public ResultVo getInfo(@PathVariable("id") String id){
        return providerService.getInfo(id);
    }
}
