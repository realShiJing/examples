package com.nchu.singleton;

/**
 * @Decription 单例模式 懒汉模式（双重检查）
 * 优点：线程安全、延时加载、效率高
 * 总结：推荐使用
 * 1、构造器私有化
 * 2、类的内部创建对象
 * 3、向外暴露一个静态的公共方法。
 * @Author yangsj
 * @Date 20191015 16:04
 **/
public class Singleton6 {
    //构造器私有化
    private Singleton6(){}

    private static volatile  Singleton6 instance;

    //向外暴露一个静态的公共方法,当调用该方法时，才创建单例对象
    public static Singleton6 getInstance() {
        //线程安全
        if(instance == null){
            synchronized (Singleton6.class){
                if(instance == null){
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
