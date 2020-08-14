package com.nchu.jvm.heap.escapeanalysis;

/**
 * @Decription 标量替换测试
 * 配置参数：-XX:+EliminateAllocations，开启标量替换。
 * -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 *
 * 未开启标量替换时花费时间更长，而且发生了GC
 * 启标量替换时花费时间更短，而且没有发生GC
 * 注意标量替换时基于逃逸分析，因此开启标量替换的前提是开启逃逸分析
 * @Author yangsj
 * @Date 2020/8/14 15:58
 **/
public class ScalarReplace {
    public static class User {
        public int id;//标量（无法再分解成更小的数据）
        public String name;//聚合量（String还可以分解为char数组）
    }

    public static void alloc() {
        User u = new User();//未发生逃逸
        u.id = 5;
        u.name = "www.atguigu.com";
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        Thread.sleep(100000000);
    }
}
