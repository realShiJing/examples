package com.example.cloud.eureka.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 15:42
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//通过服务名访问时必须要加
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
