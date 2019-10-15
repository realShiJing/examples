package com.nchu.bean;

/**
 * @Decription 父类
 * @Author yangsj
 * @Date 20191015 11:11
 **/
public class Parent {

    /**
     * @Description 'static' method declared 'final'
     * Reports methods declared final and static.
     * When a static method is overridden in a subclass it can still be accessed via the super class,
     * making a final declaration not very necessary.
     * Declaring a static method final does prevent subclasses from defining a static method with the same signature.
     *
     * static 修饰的方法：
     *      1、父类中的静态方法可以被继承，但不能被子类重写,父子类一样的方法名不会出现编译错误
     *      2、如果在子类中写一个和父类中一样的静态方法,那么该静态方法由该子类特有，两者不构成重写关系
     * final  修饰的方法
     *      1、修饰的方法表示不允许被子类重写（会出现编译错误），但是可以被子类继承，不能修饰构造方法。
     * @Author yangsj
     **/
    public static final void test(){
        System.out.print("parent");
    }


    public  void test1(){
        System.out.print("parent");
    }

    public  static  void test2(){
        System.out.print("parent");
    }


    public  final void test3(){
        System.out.print("parent");
    }
}
