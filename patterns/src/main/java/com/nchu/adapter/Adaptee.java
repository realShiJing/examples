package com.nchu.adapter;

/**
 * @Decription 目标源（路由器）：提供上网功能
 * @Author yangsj
 * @Date 2020-01-01 23:22
 **/
public class Adaptee {
    /**
     * @Description  目标源提供的功能
     * @Author yangsj
     * @Date 2020-01-01 23:35
     **/
    public void request(){
        System.out.println("连接上网络~");
    }
}
