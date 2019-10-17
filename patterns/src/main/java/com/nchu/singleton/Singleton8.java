package com.nchu.singleton;

/**
 * @Decription 枚举
 * 1、借助JDK1.5中添加枚举来实现单例模式，不仅能避免多线程的问题，而且还能防止反序列化重新创建新的对象？
 * 2、这种方式是Effective Java作者josh Bloch提倡的方式
 * @Author yangsj
 * @Date 20191016 9:55
 **/
public enum Singleton8 {
    INSTANCE
}
