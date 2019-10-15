package com.nchu.singleton;

/**
 * @Decription 单例模式 懒汉模式（同步块）
 * 非线程安全的，同步块没有保证线程的安全
 * 总结：不可用
 * 1、构造器私有化
 * 2、类的内部创建对象
 * 3、向外暴露一个静态的公共方法。
 * @Author yangsj
 * @Date 20191015 16:04
 **/
public class Singleton5 {
    //构造器私有化
    private Singleton5(){}

    private static Singleton5 instance;


    //向外暴露一个静态的公共方法,当调用该方法时，才创建单例对象

    public static  Singleton5 getInstance() {
        if(instance == null){
            //线程不安全
            synchronized (Singleton5.class){
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
