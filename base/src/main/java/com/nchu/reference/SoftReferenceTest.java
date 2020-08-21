package com.nchu.reference;

import java.lang.ref.SoftReference;

/**
 * @Decription 软引用的测试：内存不足即回收
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 * @Author yangsj
 * @Date 2020/8/21 10:13
 **/
public class SoftReferenceTest {
    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;

        @Override
        public String toString() {
            return "[id=" + id + ", name=" + name + "] ";
        }
    }

    public static void main(String[] args) {
        //创建对象，建立软引用
        SoftReference<User> userSoftRef = new SoftReference<User>(new User(1, "yangsj"));

        //从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();
        System.out.println("After GC:");
       //垃圾回收之后获得软引用中的对象
        System.out.println(userSoftRef.get());//由于堆空间内存足够，所以不会回收软引用的可达对象。
        try {
            //让系统认为内存资源紧张、不够
            //byte[] b = new byte[1024 * 1024 * 7];//内存不足，报OOM
            byte[] b = new byte[1024 * 7168 -792 * 1024];//恰好能放下数组，又放不下user的内存分配大小，又不会报OOM(根据机器灵活配置)
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //再次从软引用中获取数据
            System.out.println(userSoftRef.get());//在报OOM之前，垃圾回收器会回收软引用的可达对象。打印为null
        }
    }
}
