package com.nchu.singleton;

/**
 * @Decription 单例模式 饿汉模式（静态代码块）
 * 优点： 线程安全的，
 * 缺点： 类装载的时候对象被初始化，没有达到Lazy Loading，
 *       如果从始至终都没有使用过这个实例，则会造成内存的浪费
 * 总结：可用，但是可能会造成内存浪费
 * 1、构造器私有化
 * 2、类的内部创建对象
 * 3、向外暴露一个静态的公共方法。
 * @Author yangsj
 * @Date 20191015 16:04
 **/
public class Singleton2 {
    //构造器私有化
    private Singleton2(){}
    //类的内部创建对象
    private static  Singleton2 instance;

    static {
        instance = new Singleton2();
    }
    //向外暴露一个静态的公共方法
    public static Singleton2 getInstance() {
        return instance;
    }
}
