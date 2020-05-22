package com.nchu.cloudalibaba.sentinel.service;

import org.springframework.stereotype.Component;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/22 14:10
 **/
@Component
public class ProviderFallbackService implements ProviderService {
    @Override
    public String getInfo(Long id) {
        return "服务降级返回,---PaymentFallbackService";
    }
}
