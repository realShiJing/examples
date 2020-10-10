package com.nchu.dubbo.spi.jdk.service.impl;


import com.nchu.dubbo.spi.jdk.api.Log;

/**
 * @Decription 自定义 Log4j 日志打印实现
 * 当服务提供者提供了接口的一种具体实现后，在META-INF/services目录下创建一个以“接口全限定名”为命名的文件，内容为实现类的全限定名
 * @Author yangsj
 * @Date 2020/10/3 1:05 下午
 **/
public class Log4j implements Log {
    @Override
    public void log(String info) {
        System.out.println("Log4j:"+info);
    }
}
