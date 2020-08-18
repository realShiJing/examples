package com.nchu.gc.algorithm;

/**
 * @Decription 验证 JVM 没有使用引用计数法
 * -XX:+PrintGCDetails
 * @Author yangsj
 * @Date 2020/8/18 16:34
 **/
public class RefCountGC {
    //这个成员属性唯一的作用就是占用一点内存
    private byte[] bigSize = new byte[5 * 1024 * 1024];//5MB，仅仅意味着只要创建我这个RefCountGC对象实例，就会占用5M的堆空间

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;
        //显式的执行垃圾回收行为
        //这里发生GC，obj1和obj2能否被回收？能，证明Java没有采用引用计数算法
        System.gc();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
