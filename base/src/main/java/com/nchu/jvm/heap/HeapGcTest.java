package com.nchu.jvm.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Decription 堆GC测试
 * -Xms600m -Xmx600m
 * -XX:SurvivorRatio=8
 * @Author yangsj
 * @Date 2020/8/13 11:04
 **/
public class HeapGcTest {
    // 模拟 200k 以内的存活对象
    byte[] buffer = new byte[new Random().nextInt(1024 * 200)];


    /**
     * @Description 不断向堆中添加新对象
     * @Author yangsj
     * @Date 2020/8/13 11:06
     **/
    public static void main(String[] args) {
        ArrayList<HeapGcTest> list = new ArrayList<HeapGcTest>();
        while (true) {
            list.add(new HeapGcTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
