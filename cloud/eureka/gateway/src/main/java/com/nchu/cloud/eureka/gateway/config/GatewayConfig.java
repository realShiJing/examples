package com.nchu.cloud.eureka.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription 网关配置
 * @Author yangsj
 * @Date 2020/5/15 14:16
 **/
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route-name的路由规则
     * 当访问localhost:9527/study的时候，将会转发至https://www.yuque.com/yangshijing/cl05h3/awcthr
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public  RouteLocator customRoteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        return routes.route("path",r->r.path("/study").uri("https://www.yuque.com/yangshijing/cl05h3/awcthr")).build();
    }
}
