package com.nchu.jvm.heap;

/**
 * @Decription 配置新生代与老年代在堆结构的占比
 *  -Xms600m -Xmx600m
 *
 *  -XX:NewRatio ： 设置新生代与老年代的比例。默认值是2.
 *  -XX:SurvivorRatio ：设置新生代中Eden区与Survivor区的比例。默认值是8
 *  -XX:-UseAdaptiveSizePolicy ：关闭自适应的内存分配策略 '-'关闭,'+'打开  （暂时用不到）
 *  -Xmn:设置新生代的空间的大小。 （一般不设置）
 *
 *  如果同时配置了  -XX:NewRatio 和  -Xmn 以 -Xmn 配置的新生代大小为准
 * @Author yangsj
 * @Date 2020/8/13 10:31
 **/
public class EdenSurvivorTest {

    /**
     * @Description
     * >>jstat -gc pid
     * 配置 -XX:SurvivorRatio  参数
     *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
     * 20480.0 20480.0  0.0    0.0   163840.0 13107.3   409600.0     0.0     4480.0 770.4  384.0   75.9       0    0.000   0      0.000    0.000
     *
     * survior0区 20M，survior1区 20M，eden 160M,old区 400M
     * Eden空间和另外两个Survivor空间缺省所占的比例是8：1：1
     *
     * 未配置 -XX:SurvivorRatio  参数，JVM开启自适应的内存分配策略
     *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
     * 25600.0 25600.0  0.0    0.0   153600.0 12288.1   409600.0     0.0     4480.0 770.4  384.0   75.9       0    0.000   0      0.000    0.000
     *
     * survior0区 25M，survior1区 25M，eden 150M,old区 400M
     * Eden空间和另外两个Survivor空间缺省所占的比例是6：1：1
     * @Author yangsj
     * @Date 2020/8/13 10:36
     **/
    public static void main(String[] args) {
        System.out.println("我只是来打个酱油~");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
