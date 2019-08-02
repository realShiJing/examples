package com.nchu.bean;

import org.junit.Test;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/20 16:35
 */
public  class NowCode {
    /**
     *@description: 1、调用子类的构造方式时，会隐式的调用父类的无参构造方法
     *              2、如果想调用父类的有参构造方法，必须使用Super()显式的调用
     *
     *@auther: yangsj
     *@created: 2019/3/28 13:53
     *
     */
    @Test
    public void Test(){
        B b = new B("1");
    }

    @Test
    public void Test1(){
        String s = new String("hello");
        String t = new String("hello");
        char[] c = {'h','e','l','l','0'};
        System.out.println(s==t);
    }
    @Test
    public void Test2(){
        "Admin".toLowerCase();
    }

    @Test
    public void Test3(){
        B b = new B();
        b.test();
    }
}

class A{
    A(){
        System.out.println("父类的无参构造方法");
    }
    A(String str){
        System.out.println("父类的有参构造方法");
    }


    protected  String value1 = "";

    private  String value2 = "";

    public static  String value3 = "";

    private final String value4 = "";

    private void test1(){
        System.out.println("测试");
    }
}

class B extends A {
    B(){
        System.out.println("子类的无参构造方法");
    }
    B(String str){
        super(str);
        System.out.println("子类的有参构造方法");
    }
    public void test(){

    }
}

