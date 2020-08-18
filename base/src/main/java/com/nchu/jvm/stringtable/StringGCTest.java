package com.nchu.jvm.stringtable;

/**
 * @Decription StrtingTable的垃圾回收
 * -XX:+PrintStringTableStatistics展示字符串常量池信息
 *  -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 * @Author yangsj
 * @Date 2020/8/18 14:33
 **/
public class StringGCTest {
    public static void main(String[] args) throws InterruptedException {
        //发生垃圾回收行为
        for (int j = 0; j < 100000; j++) {
            String.valueOf(j).intern();
        }
        Thread.sleep(100000);
    }
}
