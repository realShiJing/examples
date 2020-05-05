package com.nchu.dubbo;

/**
 * @Decription 服务提供方实现接口
 * @Author yangsj
 * @Date 2020/5/4 10:24 下午
 **/
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
