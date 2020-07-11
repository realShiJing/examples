package com.nchu.syn;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Decription 分析 synchronized 锁的膨胀，降级
 * @Author yangsj
 * @Date 2020/2/17 10:03 下午
 **/
public class App {
    /**
     *   HotSpot虚拟机中，对象在内存中存储的布局可以分为3块区域：
     *   对象头
     *      对象头又分为两部分：
     *                      Mark Word 在
     *                      类型指针
     *   实例数据
     *   对齐信息
     *
     *
     *   OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           48 72 06 00 (01001000 01110010 00000110 00000000) (422472)
     *      12     4        (loss due to the next object alignment)
     *
     */
    public static void main(String[] args) {
        MySyn mySyn = new MySyn();
        System.out.println(ClassLayout.parseInstance(mySyn).toPrintable());

        // 打印对象信息
        synchronized (mySyn){
            System.out.println(ClassLayout.parseInstance(mySyn).toPrintable());
        }

    }
}
