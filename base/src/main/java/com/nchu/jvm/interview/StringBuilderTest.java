package com.nchu.jvm.interview;

/**
 * @Decription 面试题：
 * 方法中定义的局部变量是否线程安全？具体情况具体分析
 * 1. 如果是引用类型参数和返回对象的局部变量可能会被线程共享，有线程安全的问题
 * 2. 如果局部变量在方法内部创建，并在结束返回前消亡，则没有线程安全的问题
 * @Author yangsj
 * @Date 2020/8/11 15:09
 **/
public class StringBuilderTest {

    //s1的声明方式是线程安全的，s1在方法method1内部消亡了
    public static void method1(){
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
    }

    //stringBuilder的操作过程：是不安全的，因为method2可以被多个线程调用
    public static void method2(StringBuilder stringBuilder){
        stringBuilder.append("a");
        stringBuilder.append("b");
    }

    //s1的操作：是线程不安全的 有返回值，可能被其他线程共享
    public static StringBuilder method3(){
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1;
    }

    //s1的操作：是线程安全的 ，StringBuilder的toString方法是创建了一个新的String，s1在内部消亡了
    public static String method4(){
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1.toString();
    }

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        new Thread(()->{
            s.append("a");
            s.append("b");
        }).start();

        method2(s);

    }
}
