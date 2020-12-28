package com.nchu.sentinel.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @Description  Sentinel 控制台生效，需配置JVM参数
 * -Dcsp.sentinel.dashboard.server=localhost:9000 -Dproject.name=SentinelDemo
 * @Author yangsj
 * @Date 2020/12/28 14:18
 **/
@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) {
        // 设置系统属性 project.name，提供给 Sentinel 读取
        System.setProperty("project.name", "SentinelDemo");
        SpringApplication.run(Application.class, args);
    }

}
