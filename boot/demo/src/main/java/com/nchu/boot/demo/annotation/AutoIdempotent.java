package com.nchu.boot.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Description 用于标记需要实现幂等性方法的注解
 * METHOD：表示用于方法
 * RUNTIME：表示运行时
 * @Author yangsj
 * @Date 2020/4/26 18:39
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {
}
