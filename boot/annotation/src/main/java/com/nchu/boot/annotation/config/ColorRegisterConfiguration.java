package com.nchu.boot.annotation.config;

import com.nchu.boot.annotation.pojo.Yellow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/16 下午3:17
 **/
@Configuration
public class ColorRegisterConfiguration {

    /**
     * @Description   创建的Bean的name 默认是方法名
     * @Author yangsj
     * @Date 2020/11/16 下午3:21
     **/
    @Bean("yellow")
    public Yellow createYellow(){
        return new Yellow();
    }
}
