package com.nchu.cloudalibaba.sentinel.controller;

import com.nchu.cloudalibaba.sentinel.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @Decription OpenFeign 调用
 * @Author yangsj
 * @Date 2020/5/22 14:12
 **/
public class ProviderController {
    @Resource
    private ProviderService providerService;

    @GetMapping(value = "/consumer/provider/{id}")
    public String getInfo(@PathVariable("id") Long id)
    {
        return providerService.getInfo(id);
    }
}
