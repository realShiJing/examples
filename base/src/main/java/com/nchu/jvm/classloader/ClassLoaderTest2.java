package com.nchu.jvm.classloader;

/**
 * @Decription 类加载器
 * @Author yangsj
 * @Date 2020/8/3 15:43
 **/
public class ClassLoaderTest2 {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader1);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);


    }
}
