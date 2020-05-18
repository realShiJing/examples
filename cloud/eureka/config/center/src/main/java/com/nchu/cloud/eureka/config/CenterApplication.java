package com.nchu.cloud.eureka.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CenterApplication {


    /**
     * @Description 启动完成后，访问 http://localhost:3344/config-dev.yml 测试是否成功
     * @Author yangsj
     * @Date 2020/5/18 12:28
     **/
    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class, args);
    }

}
