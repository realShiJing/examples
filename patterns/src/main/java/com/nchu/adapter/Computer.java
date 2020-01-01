package com.nchu.adapter;

/**
 * @Decription 电脑（客户端）：需要上网，但是插不上网线，没法直接使用路由器提供的功能，必须使用适配器进行适配
 * @Author yangsj
 * @Date 2020-01-01 23:21
 **/
public class Computer {

    /**
     * @Description  客户端通过适配器访问路由器提供的网络
     * @Author yangsj
     * @Date 2020-01-01 23:38
     **/
    public void net(Target adapter){
        adapter.handle();
    }
}
