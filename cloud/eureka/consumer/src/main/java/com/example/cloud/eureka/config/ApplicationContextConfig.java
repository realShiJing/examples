package com.example.cloud.eureka.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Decription 消费者配置
 * @Author yangsj
 * @Date 2020/5/8 15:42
 **/
@Configuration
public class ApplicationContextConfig {

    /**
     * RestTemplate 提供模板化的方法让开发者能更简单地发送 HTTP 请求
     */
    @Bean
    @LoadBalanced//通过服务名访问时必须要加
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
