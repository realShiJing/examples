package com.nchu.bean;

/**
 * @Decription 子类
 * @Author yangsj
 * @Date 20191015 11:12
 **/
public class Son extends Parent {

    //'test()' cannot override 'test()' in 'com.nchu.bean.Parent'; overridden method is final
    /*public void test(){

    }*/

    public  void  test1(){
        System.out.println("son");
    }

    public static  void  test2(){
        System.out.println("son");
    }
}
