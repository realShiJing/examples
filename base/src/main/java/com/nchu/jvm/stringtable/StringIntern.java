package com.nchu.jvm.stringtable;

/**
 * @Decription jdk7及以后的intern()方法调用，如果字符创常量池中没有字符，但是堆中有，
 * 此时不会在字符串常量池中再新建一个字符串，而是新建一个引用，指向堆中已经存在的这个字符串
 * @Author yangsj
 * @Date 2020/8/17 19:25
 **/
public class StringIntern {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了"1".即此方法可理解为无效
        String s2 = "1";
        //s  指向堆空间"1"的内存地址//s2 指向字符串常量池已存在的"1"的内存地址
        System.out.println(s == s2);//jdk6：false   jdk7/8：false


        String s3 = new String("1") + new String("1");//s3变量记录的地址为堆中11，此时字符串常量池当中并不存在11
        // 在字符串常量池中生成"11"
        // 如何理解：jdk6:创建了一个新的对象"11",也就有新的地址
        // jdk7:此时常量中并没有创建"11",而是创建一个指向堆空间中new String("11")的地址
        s3.intern();
        String s4 = "11";//s4变量记录的地址，使用的是上一行代码执行后在常量池生成的，指向11的地址
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }
}
