package com.nchu.jvm.stringtable;

/**
 * @Decription intern()练习2
 * 字符串常量池和堆中各有一份字符串实例
 * @Author yangsj
 * @Date 2020/8/18 9:14
 **/
public class StringExer2 {
    public static void main(String[] args) {
        String s1 = new String("ab");//执行完以后，会在字符串常量池中会生成"ab"。但是指向堆aa
//      String s1 = new String("a") + new String("b");////执行完以后，不会在字符串常量池中会生成"ab"
        s1.intern();//检查字符串常量池当中有ab
        String s2 = "ab";//指向字符串常量池已经存在的ab
        System.out.println(s1 == s2); //false
    }
}
