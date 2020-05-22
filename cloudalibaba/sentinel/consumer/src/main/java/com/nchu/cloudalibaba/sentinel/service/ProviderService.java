package com.nchu.cloudalibaba.sentinel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/22 14:09
 **/
@FeignClient(value = "nacos-provicer",fallback = ProviderFallbackService.class)
public interface ProviderService {

    @GetMapping("/provider/getInfo/{id}")
    public String getInfo(@PathVariable("id")Long id);
}
