package com.nchu.jvm.classloader;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

/**
 * @Decription 类加载器
 * @Author yangsj
 * @Date 2020/8/3 15:43
 **/
public class ClassLoaderTest1 {

    public static void main(String[] args) {
        System.out.println("===========启动类加载器搜索范围==========");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        // 获取启动类加载器能够加载的类路径
        for (URL urL : urLs) {
            System.out.println(urL);

        }
        //从上面的路径中随意选择一个类 看看他的类加载器是什么
        //Provider位于 /jdk1.8.0_171.jdk/Contents/Home/jre/lib/jsse.jar 下，引导类加载器加载它
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);//null

        System.out.println("===========扩展类加载器搜索范围==========");
        String extDir = System.getProperty("java.ext.dirs");
        System.out.println(extDir);

        //从上面的路径中随意选择一个类 看看他的类加载器是什么:拓展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@87aac27

    }
}
