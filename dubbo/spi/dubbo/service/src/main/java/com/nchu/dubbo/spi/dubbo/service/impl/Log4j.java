package com.nchu.dubbo.spi.dubbo.service.impl;


import com.nchu.dubbo.spi.dubbo.api.Log;
import org.apache.dubbo.common.URL;

/**
 * @Decription 自定义 Log4j 日志打印实现
 * @Author yangsj
 * @Date 2020/10/3 1:05 下午
 **/
public class Log4j implements Log {
    @Override
    public void log(String info) {
        System.out.println("Log4j:"+info);
    }

    @Override
    public void log(URL url, String info) {
        System.out.println("Log4j:"+info+"|url:"+url);
    }
}
