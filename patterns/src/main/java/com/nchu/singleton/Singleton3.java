package com.nchu.singleton;

/**
 * @Decription 单例模式 懒汉模式(当调用该方法时，才创建单例对象)
 * 优点：起到了Lazy Loading 的效果，但是只能在单线程下使用
 * 缺点：线程不安全，如果在多线程的环境下，有一个线程通过了 （instance == null）的判断，
 *      还未来的及往下执行，另一个线程也通过了这个判断，这时就会创建多个实例。
 * 总结：不可用
 * 1、构造器私有化
 * 2、类的内部创建对象
 * 3、向外暴露一个静态的公共方法。
 * @Author yangsj
 * @Date 20191015 16:04
 **/
public class Singleton3 {
    //构造器私有化
    private Singleton3(){}

    private static Singleton3 instance;


    //向外暴露一个静态的公共方法,当调用该方法时，才创建单例对象
    public static Singleton3 getInstance() {
        if(instance == null){
            instance = new Singleton3();
        }
         return instance;
    }
}
