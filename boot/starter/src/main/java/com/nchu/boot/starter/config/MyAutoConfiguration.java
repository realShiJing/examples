package com.nchu.boot.starter.config;

import com.nchu.boot.starter.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription 自定义自动配置类
 * @Conditional 第二次过滤
 * @Author yangsj
 * @Date 2020/11/17 下午2:14
 **/
@Configuration
//@ConditionalOnBean(User.class)//当前IOC容器中含有该Bean时生效
//@ConditionalOnClass(User.class)// 当前classpaths类路径下含有该Bean class文件时生效
@ConditionalOnProperty(name = "synchronize",havingValue = "true")// 相关配置文件满足条件时生效
public class MyAutoConfiguration {
    static {
        System.out.println("自定义自动配置类 MyAutoConfiguration 初始化 ！！！");
    }

    @Bean("user")
    public User createUser() {
        return new User();
    }
}
