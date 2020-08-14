package com.nchu.jvm.heap.escapeanalysis;

/**
 * @Decription 基于逃逸分析的同步省略（锁消除）
 * @Author yangsj
 * @Date 2020/8/14 15:47
 **/
public class SynchronizedTest {


    /**
     * @Description 因为  StringBuffer 对象不会逃逸到方法外，因此JVM优化会消除StringBuffer的同步
     * @Author yangsj
     * @Date 2020/8/14 15:48
     **/
    public static String createStringBuffer(String s1,String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }
}
