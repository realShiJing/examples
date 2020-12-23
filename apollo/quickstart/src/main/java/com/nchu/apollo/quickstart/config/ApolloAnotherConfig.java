package com.nchu.apollo.quickstart.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription 可以注入多个 namespace 并指定加载顺序
 * @Author yangsj
 * @Date 2020/12/23 上午11:58
 **/
@Configuration
@EnableApolloConfig(value = {"spring-rocketmq","organization5.spring-boot-http"},order = 1)
public class ApolloAnotherConfig {
}
