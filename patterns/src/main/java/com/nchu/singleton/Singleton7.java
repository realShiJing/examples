package com.nchu.singleton;

/**
 * @Decription 静态内部类
 * 1、静态内部类方式在Singleton7类装载的时候并不会立即实例化，而是在需要实例化时，
 *    调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton7的实例化，保证了Lazy Loading
 *
 * 2、类的静态属性只会在第一次加载类的时候初始化,JVM帮助我们保证了线程的安全性，
 *    在类进行初始化时，别的线程是无法进入的？
 * 优点：线程安全、延时加载、效率高
 * 总结：推荐使用
 * @Author yangsj
 * @Date 20191016 9:55
 **/
public class Singleton7 {
    //私有的构造器，禁止在外部进行创建
    private Singleton7(){}

    //静态内部类
    private static class SingletonInstance{
        private static final Singleton7 INSTANCE = new Singleton7();
    }
    //对外提供获取实例的方法
    public Singleton7 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
