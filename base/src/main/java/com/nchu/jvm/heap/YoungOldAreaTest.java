package com.nchu.jvm.heap;

/**
 * @Decription 测试：大对象直接进入老年代
 * -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @Author yangsj
 * @Date 2020/8/13 11:46
 **/
public class YoungOldAreaTest {


    /**
     * @Description
     *    新生代 20m ，Eden 16m， s0 2m， s1 2m
     *    老年代 40m
     * Heap
     *  PSYoungGen      total 18432K, used 3631K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 16384K, 22% used [0x00000000fec00000,0x00000000fef8bf20,0x00000000ffc00000)
     *   from space 2048K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x0000000100000000)
     *   to   space 2048K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffe00000)
     *  ParOldGen       total 40960K, used 20480K [0x00000000fc400000, 0x00000000fec00000, 0x00000000fec00000)
     *   object space 40960K, 50% used [0x00000000fc400000,0x00000000fd800010,0x00000000fec00000)
     *  Metaspace       used 3514K, capacity 4498K, committed 4864K, reserved 1056768K
     *   class space    used 387K, capacity 390K, committed 512K, reserved 1048576K
     *
     *   由GC日志可以看出 ParOldGen 总共 40 M ，已被使用 20 M
     * @Author yangsj
     * @Date 2020/8/13 11:47
     **/
    public static void main(String[] args) {
        //Eden 区无法存放buffer  晋升老年代
        byte[] buffer = new byte[1024 * 1024 * 20];//20m
    }
}
