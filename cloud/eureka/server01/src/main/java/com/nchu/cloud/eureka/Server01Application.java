package com.nchu.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @Description eureka服务端启动类
 * @Author yangsj
 * @Date 2020/5/9 10:43
 **/
@SpringBootApplication
@EnableEurekaServer
public class Server01Application {

    public static void main(String[] args) {
        SpringApplication.run(Server01Application.class, args);
    }

}
