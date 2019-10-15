package com.nchu.singleton;

/**
 * @Decription 单例模式 懒汉模式（同步方法）
 * 优点：线程安全
 * 缺点：效率低、每个线程在想获得类的实例时，都要进行同步。
 * 总结：不推荐
 * 1、构造器私有化
 * 2、类的内部创建对象
 * 3、向外暴露一个静态的公共方法。
 * @Author yangsj
 * @Date 20191015 16:04
 **/
public class Singleton4 {
    //构造器私有化
    private Singleton4(){}

    private static Singleton4 instance;


    //向外暴露一个静态的公共方法,当调用该方法时，才创建单例对象
    //同步
    public static synchronized Singleton4 getInstance() {
        if(instance == null){
            instance = new Singleton4();
        }
         return instance;
    }
}
