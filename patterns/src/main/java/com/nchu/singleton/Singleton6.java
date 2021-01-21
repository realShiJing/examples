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

    /**
     * 如果不加上 volatile 关键字 ，有生成未初始化完成的对象的可能
     * 因为对象初始化 new 命令最终编译成字节码指令会生成三条指令
     * 1. 分配内存空间
     * 2. 初始化对象
     * 3. 将内存空间赋值给变量
     *
     * 由于编译器优化，第二步和第三步有可能会发生指令重排
     * 当第一个线程获取锁成功，如果发生了指令重排，第二步先与第三步执行，此时变量instance不为null
     * 如果此时CPU上下切换到第二个线程，第二个线程判断instance 不为 null，然后将未初始化完成的对象返回
     * 这样第二个线程获取到的单例对象就不是正确初始化完成的对象，这个线程拿着这个对象去做后续操作剧有可能出现问题
     */
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
