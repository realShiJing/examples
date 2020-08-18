package com.nchu.jvm.stringtable;

import org.junit.Test;

/**
 * @Decription String的基本使用:体现String的不可变性
 * @Author yangsj
 * @Date 2020/8/17 17:07
 **/
public class StringTableTest {
    @Test
    public void test1() {
        String s1 = "abc";//字面量定义的方式，"abc"存储在字符串常量池中
        String s2 = "abc";//s1,s2指向同一个abc
        s1 = "hello";//s1指向字符串常量池新开辟了的hello，不影响原有的abc

        System.out.println(s1 == s2);//判断地址： false

        System.out.println(s1);//hello
        System.out.println(s2);//abc

    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";
        System.out.println(s2);//abcdef
        System.out.println(s1);//abc
    }

    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = s1.replace('a', 'm');
        System.out.println(s1);//abc
        System.out.println(s2);//mbc
    }

    @Test
    public void test4(){
        String s1 = "a" + "b" + "c";//编译期优化：等同于"abc"
        String s2 = "abc"; //"abc"一定是放在字符串常量池中，将此地址赋给s2
        /*
         * 最终.java编译成.class,再执行.class
         * String s1 = "abc";
         * String s2 = "abc"
         */
        System.out.println(s1 == s2); //true
        System.out.println(s1.equals(s2)); //true
    }

    @Test
    public void test5(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";//编译期优化
        //如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容为拼接的结果：javaEEhadoop
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        //intern():判断字符串常量池中是否存在javaEEhadoop值，如果存在，则返回常量池中javaEEhadoop的地址；
        //如果字符串常量池中不存在javaEEhadoop，则在常量池中加载一份javaEEhadoop，并返回次对象的地址。
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }


    /**
     * @Description 变量拼接:StringBuilder原理
     * @Author yangsj
     * @Date 2020/8/17 18:56
     **/
    @Test
    public void test6(){
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        /*
        如下的s1 + s2 的字节码执行细节：(变量s是我临时定义的）
        ① StringBuilder s = new StringBuilder();
        ② s.append("a")
        ③ s.append("b")
        ④ s.toString()  --> 约等于 new String("ab"),不会放到常量池中

        补充：在jdk5.0之后使用的是StringBuilder,
        在jdk5.0之前使用的是StringBuffer
         */
        String s4 = s1 + s2;//
        System.out.println(s3 == s4);//false
    }

    /**
     * @Description final常量拼接
     *  1. 字符串拼接操作不一定使用的是StringBuilder!
     *  2.如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化，不采用StringBuilder的方式。、
     *  3. 针对于final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上final的时候建议使用上。
     * @Author yangsj
     * @Date 2020/8/17 19:02
     **/
    @Test
    public void test7(){
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//true
    }

    //练习：
    @Test
    public void test8(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//s4:常量
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5);//true

    }

    
    /**
     * @Description 拼接操作与append的效率对比
     *
     * 通过StringBuilder的append()的方式添加字符串的效率要远高于使用String的字符串拼接方式！
     *  1.StringBuilder的append()的方式：自始至终中只创建过一个StringBuilder的对象。
     *  2.使用String的字符串拼接方式：创建过多个StringBuilder和String的对象。内存中由于创建了较多的StringBuilder和String的对象，内存占用更大；如果进行GC，还需要花费额外的时间。
     *  改进的空间：
     *  在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值highLevel的情况下,建议使用构造器实例化，手动指定StringBuilder容器上限大小。
     *  防止超过默认容量大小16而扩容，扩容会将原有的字符串赋值到新的容器当中，原有的容器变成垃圾需要回收。
     * @Author yangsj
     * @Date 2020/8/17 19:04
     **/
    @Test
    public void test9(){

        long start = System.currentTimeMillis();

        method1(100000);//6599
        //method2(100000);//20

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));
    }

    
    /**
     * @Description TODO 
     * @Author yangsj
     * @Date 2020/8/17 19:04
     **/
    public void method1(int highLevel){
        String src = "";
        for(int i = 0;i < highLevel;i++){
            src = src + "a";//每次循环都会创建一个StringBuilder、String
        }

    }

    public void method2(int highLevel){
        //只需要创建一个StringBuilder
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }

    /**
     * @Description new String()会创建几个对象
     * @Author yangsj
     * @Date 2020/8/17 19:08
     **/
    public static void main(String[] args) {
        /**
         *  new String("ab")会创建几个对象？
         * 看字节码，就知道是两个。
         * 一个对象是：new关键字在堆空间创建的
         * 另一个对象是：字符串常量池中的对象"ab"。 字节码指令：ldc
         * 但注意：str此时指向的是堆空间当中的对象
         */
        //String str = new String("ab");

        /**
         * new String("a") + new String("b")呢？
         * 对象1：StringBuilder 用于拼接
         * 对象2：String        字符串 "a"
         * 对象3：常量池 "a"
         * 对象4：String        字符串"b"
         * 对象5：常量池 "b"
         * 对象6：StringBuilder.toString(),因为该方法是使永字符数组创建String对象，所以不会添加到常量池中
         * Class StringBuilder{
         *  char[] value;
         *   @Override
         *   public String toString() {
         *       // Create a copy, don't share the array
         *       return new String(value, 0, count);
         *   }
         * }
         *
         *
         */
        String str = new String("a") + new String("b");
    }
}
