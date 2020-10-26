package com.nchu.dubbo.rpc.service;

/**
 * HelloServiceImpl
 *
 * @author william.liangf
 */
public class HelloServiceImpl implements HelloService {

    public String hello(String name) {
        return "Hello" + name;
    }

}