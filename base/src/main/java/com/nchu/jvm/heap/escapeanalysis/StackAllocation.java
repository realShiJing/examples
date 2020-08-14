package com.nchu.jvm.heap.escapeanalysis;

/**
 * @Decription 栈上分配测试
 * -Xmx256m -Xms256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 以下代码，维护10000000个对象
 *
 * -Xmx256m -Xms256m -XX:-DoEscapeAnalysis
 * 如果关闭逃逸分析，会发生GC，且耗时较长，说明堆空间eden区已满
 *
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 *  调大堆内存大小，避免发生GC,并且关闭栈上分配， 从VisualVM 抽样器查看堆内User实例数，发现有10000000个
 *
 * -XX:+DoEscapeAnalysis
 * 如果开启逃逸分析，不会发生GC，且耗时较短，说明对象并未分配到堆上(这里实际上采用的是标量替换)
 * 从VisualVM 抽样器查看堆内User实例数，发现并没有10000000个
 * @Author yangsj
 * @Date 2020/8/14 11:41
 **/
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();//未发生逃逸
    }

    static class User {

    }
}
