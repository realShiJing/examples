package com.nchu.java8;

import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Decription jdk1.8时间测试
 * @Author yangsj
 * @Date 20190819 14:57
 **/
public class LocalTimeDemo {

    /**
     * @Description TODO
     * @Author yangsj
     * @Date 2019/8/19 14:59
     **/
    @Test
    public void test1(){
        LocalTime now = LocalTime.now();
        //当前时间（包含毫秒数）15:00:04.156
        System.out.println("当前时间："+ now);

        //当前时间（清除毫秒数）
        now = LocalTime.now().withNano(0);
        System.out.println("当前时间："+ now);

        //构造时间
        LocalTime zero = LocalTime.of(0, 0, 0);
        System.out.println("零点："+ zero);

        //支持一下格式的时间转换
        //12:00
        //12:01:02
        //12:01:02.345
        LocalTime mid = LocalTime.parse("12:00:01");
        System.out.println("中午："+ mid);
    }

    @Test
    public void test2(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);
    }
}
