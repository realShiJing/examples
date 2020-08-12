package com.nchu.jvm.heap;

/**
 * @Decription 查看堆内存大小
 *
 * 默认情况下，初始内存大小：物理内存大小/64;最大内存大小：物理内存大小/4。
 *
 * -Xms600m -Xmx600m
 *
 * -Xms : 575M
 * -Xmx : 575M
 *
 * Q：为什么堆内存设置了600M，但是实际却是 575M
 * A:因为  eden + from (两个survivor二选1) + oldGen
 * (153600K + 25600K + 409600K )/1024 = 575M
 *
 * Heap
 *  PSYoungGen      total 179200K, used 12288K [0x00000000f3800000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 153600K, 8% used [0x00000000f3800000,0x00000000f44001b8,0x00000000fce00000)
 *   from space 25600K, 0% used [0x00000000fe700000,0x00000000fe700000,0x0000000100000000)
 *   to   space 25600K, 0% used [0x00000000fce00000,0x00000000fce00000,0x00000000fe700000)
 *  ParOldGen       total 409600K, used 0K [0x00000000da800000, 0x00000000f3800000, 0x00000000f3800000)
 *   object space 409600K, 0% used [0x00000000da800000,0x00000000da800000,0x00000000f3800000)
 *  Metaspace       used 3523K, capacity 4498K, committed 4864K, reserved 1056768K
 *   class space    used 387K, capacity 390K, committed 512K, reserved 1048576K
 *
 *
 * @Author yangsj
 * @Date 2020/8/12 10:09
 **/
public class HeapSpaceInitial {
    public static void main(String[] args) {
        //返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms : " + initialMemory + "M");//-Xms : 123M
        System.out.println("-Xmx : " + maxMemory + "M");//-Xmx : 1799M

        // 未设置初始堆内存和最大堆内存的情况下，可以算出系统总内存
        System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");//系统内存大小为：7.6875G
        System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");//系统内存大小为：7.02734375G
    }
}
