package com.nchu.boot.annotation;

import com.nchu.boot.annotation.config.ColorConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/16 下午3:14
 **/
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ColorConfiguration.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }
}
