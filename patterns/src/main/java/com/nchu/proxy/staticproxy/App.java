package com.nchu.proxy.staticproxy;

import org.junit.Test;

/**
 * @Decription 静态代理测试
 * @Author yangsj
 * @Date 2019-11-17 14:28
 **/
public class App {

    @Test
    public void test(){
        Subject subject = new ProxySubject();
        subject.doOperation();
    }
}
