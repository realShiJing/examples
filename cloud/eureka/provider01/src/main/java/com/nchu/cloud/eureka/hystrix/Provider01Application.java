package com.nchu.cloud.eureka.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 14:29
 **/
@SpringBootApplication
@EnableEurekaClient//开启eureka
@EnableDiscoveryClient// 开启服务发现
public class Provider01Application {
    public static void main(String[] args) {
        SpringApplication.run(Provider01Application.class, args);
    }
}
