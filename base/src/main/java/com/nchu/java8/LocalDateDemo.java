package com.nchu.java8;

import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.DayOfWeek.MONDAY;

/**
 * @Decription jdk1.8日期处理
 * @Author yangjs
 * @Date 20190819 11:36
 **/
public class LocalDateDemo {


    /**
     * @Description 日期的获取·
     * @Author yangsj
     * @Date 2019/8/19 11:57
     **/
    @Test
    public void test1(){
        LocalDate now = LocalDate.now();
        //2019-08-19
        System.out.println("现在的时间是："+now);

        LocalDate date = LocalDate.of(1995, 8, 04);
        //1995-08-04
        System.out.println("根据年月日获取的日期是："+ date);
        // 严格按照 yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        date = LocalDate.parse("1995-08-04");
        //1995-08-04
        System.out.println("根据字符串获取的日期是：" + date);

        //无效日期也无法编译通过
        //java.time.format.DateTimeParseException: Text '1995-02-30' could not be parsed: Invalid date 'FEBRUARY 30'
        date = LocalDate.parse("1995-02-30");
    }


    /**
     * @Description 常用日期方法
     * @Author yangsj
     * @Date 2019/8/19 14:20
     **/
    @Test
    public void test2(){
        LocalDate today = LocalDate.now();
        //当月的第一天
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当月的第一天是："+ firstDayOfMonth);

        //当月的第二天
        LocalDate sencodDayOfMonth = today.withDayOfMonth(2);
        System.out.println("当月的第二天是："+ sencodDayOfMonth);

        //当月的最后一天
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月的最后一天是：" + lastDayOfMonth);

        //取下一天
        LocalDate plusDays = lastDayOfMonth.plusDays(1);
        System.out.println("加一天之后：" + plusDays);

        //当月第一个周一
        LocalDate Monday = today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println("当月的第一个周一是："+ Monday);

    }
}
