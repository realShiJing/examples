package com.example.boot.config;

import com.example.boot.interceptor.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Decription Spring Boot 扩展配置
 * @Author yangsj
 * @Date 2020/4/23 11:42
 **/
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    // 自定义拦截器
    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);
    }
}