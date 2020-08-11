package com.nchu.jvm.classloader;

/**
 * @Decription 类的加载
 * @Author yangsj
 * @Date 2020/8/1 12:25 上午
 **/
public class ClassInitTest {


    static class Father{
        static {
            A = 2;
        }
        public static int A = 1;
    }

    static class Son extends Father{
        private static int B = A;
    }


    public static void main(String[] args) {
        System.out.println(Son.B);
    }
}
