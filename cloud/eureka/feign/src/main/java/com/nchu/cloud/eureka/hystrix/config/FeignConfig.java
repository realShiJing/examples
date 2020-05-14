package com.nchu.cloud.eureka.hystrix.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription OpenFeignClient配置
 * @Author yangsj
 * @Date 2020/5/13 11:37
 **/
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
