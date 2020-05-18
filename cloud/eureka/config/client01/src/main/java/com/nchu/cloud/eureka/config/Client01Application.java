package com.nchu.cloud.eureka.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Client01Application {

    public static void main(String[] args) {
        SpringApplication.run(Client01Application.class, args);
    }

}
