package com.nchu.jvm.stringtable;

/**
 * @Decription 堆中和字符串常量池中各有一份字符串实例
 * @Author yangsj
 * @Date 2020/8/18 9:11
 **/
public class StringIntern1 {
    public static void main(String[] args) {
        // 字符串拼接使用StringBuilder.toString()方法，只是新建一个字符串实例，并没有将该字符串添加到字符串常量池中
        String s3 = new String("1") + new String("1");//new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在"11"呢？答案：不存在！！
        String s4 = "11";//在字符串常量池中直接生成对象"11"
        String s5 = s3.intern();//此时s3.intern全程OB视角，没作为。判断字符串常量池存在11，将s5指向字符串常量池
        System.out.println(s3 == s4);//false
        System.out.println(s5 == s4);//true
    }
}
