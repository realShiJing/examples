package com.nchu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Decription 实现线程安全的SimpleDateFormat
 * @Author yangsj
 * @Date 2020/2/7 9:29 下午
 **/

public class SafeDateFormat {
    //定义ThreadLocal变量
    static final ThreadLocal<DateFormat>  tl=ThreadLocal.withInitial(
            ()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    static DateFormat get(){
        return tl.get();
    }
}
//不同线程执行下面代码
//返回的df是不同的
// DateFormat df =  SafeDateFormat.get()；