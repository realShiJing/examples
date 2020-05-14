package com.example.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//开启eureka
@EnableDiscoveryClient// 开启服务发现
public class Provider02Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider02Application.class, args);
    }

}