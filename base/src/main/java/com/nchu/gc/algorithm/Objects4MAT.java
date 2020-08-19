package com.nchu.gc.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Decription MAT 分析内存泄漏
 * outgoing references 对象的依赖对象
 * incoming references  对象的被依赖对象
 * （Shallow Heap）浅堆代表了对象本身的内存占用
 * (Retained Heap) 深堆指的是一个对象被垃圾回收后，能够释放的内存大小，这些被释放的对象集合，叫做保留集
 * 支配树：查看对象的依赖关系（GCRoots为起点）
 * 线程：查看线程栈帧中的活跃对象
 * 柱状图：查看对象的实例数及深堆大小
 * @Author yangsj
 * @Date 2020/8/19 10:36
 **/
public class Objects4MAT {

    /**
     * @Description A 依赖 B
     * @Author yangsj
     * @Date 2020/8/19 15:19
     **/
    static class A4MAT {
        B4MAT b4MAT = new B4MAT();
    }


    /**
     * @Description  B 依赖 C
     * @Author yangsj
     * @Date 2020/8/19 15:19
     **/
    static class B4MAT {
        C4MAT c4MAT = new C4MAT();
    }


    /**
     * @Description C 依赖 list
     * @Author yangsj
     * @Date 2020/8/19 15:19
     **/
    static class C4MAT {
        List<String> list = new ArrayList<>();
    }



    /**
     * @Description 支配树模块
     * @Author yangsj
     * @Date 2020/8/19 15:22
     **/
    static class DominatorTreeDemo1 {
        DominatorTreeDemo2 dominatorTreeDemo2;

        public void setValue(DominatorTreeDemo2 value) {
            this.dominatorTreeDemo2 = value;
        }
    }

    static class DominatorTreeDemo2 {
        DominatorTreeDemo1 dominatorTreeDemo1;

        public void setValue(DominatorTreeDemo1 value) {
            this.dominatorTreeDemo1 = value;
        }
    }

    static class Holder {
        DominatorTreeDemo1 demo1 = new DominatorTreeDemo1();
        DominatorTreeDemo2 demo2 = new DominatorTreeDemo2();

        Holder() {
            demo1.setValue(demo2);
            demo2.setValue(demo1);
        }

        private boolean aBoolean = false;
        private char aChar = '\0';
        private short aShort = 1;
        private int anInt = 1;
        private long aLong = 1L;
        private float aFloat = 1.0F;
        private double aDouble = 1.0D;
        private Double aDouble_2 = 1.0D;
        private int[] ints = new int[2];
        private String string = "1234";
    }


    /**
     * @Description 生产大量对象
     * @Author yangsj
     * @Date 2020/8/19 15:22
     **/
    Runnable runnable = () -> {
        Map<String, A4MAT> map = new HashMap<>();

        IntStream.range(0, 100).forEach(i -> {
            byte[] bytes = new byte[1024 * 1024];
            String str = new String(bytes).replace('\0', (char) i);
            A4MAT a4MAT = new A4MAT();
            a4MAT.b4MAT.c4MAT.list.add(str);

            map.put(i + "", a4MAT);
        });

        Holder holder = new Holder();

        try {
            //sleep forever , retain the memory
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    void startHugeThread() throws Exception {
        new Thread(runnable, "huge-thread").start();
    }

    public static void main(String[] args) throws Exception {
        Objects4MAT objects4MAT = new Objects4MAT();
        objects4MAT.startHugeThread();
    }
}
