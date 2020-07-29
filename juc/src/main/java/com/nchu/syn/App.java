package com.nchu.syn;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Decription 分析 synchronized 锁的升级过程
 *
 *  HotSpot虚拟机中，对象在内存中存储的布局可以分为3块区域：
 *     对象头
 *        对象头又分为两部分：
 *              Mark Word
 *              类型指针
 *     实例数据
 *     对齐信息
 * |--------------------------------------------------------------------------------------------------------------|
 * |                                              Object Header (128 bits)                                        |
 * |--------------------------------------------------------------------------------------------------------------|
 * |                        Mark Word (64 bits)                                    |      Klass Word (64 bits)    |
 * |--------------------------------------------------------------------------------------------------------------|
 * |  unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  无锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |  thread:54 |         epoch:2      | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  偏向锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                     ptr_to_lock_record:62                            | lock:2 |     OOP to metadata object   |  轻量锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                     ptr_to_heavyweight_monitor:62                    | lock:2 |     OOP to metadata object   |  重量锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                                                                      | lock:2 |     OOP to metadata object   |    GC
 * |--------------------------------------------------------------------------------------------------------------|
 *
 *  lock:  锁状态标记位，该标记的值不同，整个 mark word 表示的含义不同。
 *  biased_lock：偏向锁标记，为1时表示对象启用偏向锁，为0时表示对象没有偏向锁。
 *  |--------------------|--------------------|--------------------|
 *  |    biased_lock     |        lock        |        锁状态
 *  |--------------------|--------------------|--------------------|
 *  |         0          |          01        |        无锁
 *  |--------------------|--------------------|--------------------|
 *  |         1          |          01        |        偏向锁
 *  |--------------------|--------------------|--------------------|
 *  |         0          |          00        |        轻量级锁
 *  |--------------------|--------------------|--------------------|
 *  |         0          |          10        |        重量级锁
 *  |--------------------|--------------------|--------------------|
 *  |         0          |          11        |        GC标记
 *  |--------------------|--------------------|--------------------|
 *
 *  age：Java GC标记位对象年龄。
 *  identity_hashcode：对象标识Hash码，采用延迟加载技术。当对象使用HashCode()计算后，并会将结果写到该对象头中。当对象被锁定时，该值会移动到线程Monitor中。
 *  thread：持有偏向锁的线程ID和其他信息。这个线程ID并不是JVM分配的线程ID号，和Java Thread中的ID是两个概念。
 *  epoch：偏向时间戳。
 *  ptr_to_lock_record：指向栈中锁记录的指针。
 *  ptr_to_heavyweight_monitor：指向线程Monitor的指针。
 *
 * @Author yangsj
 * @Date 2020/2/17 10:03 下午
 **/
public class App {


    /**
     * @Description 无锁 0 01
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     * @Author yangsj
     * @Date 2020/7/29 15:26
     **/
    @Test
    public void noLock() throws InterruptedException {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
    /**
     * @Description 偏向锁 1 01
     *
     * JVM默认延时加载偏向锁
     * 占用 thread 和 epoch 的 位置的均为0，说明当前偏向锁并没有偏向任何线程。此时这个偏向锁正处于可偏向状态，准备好进行偏向了！
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5) |
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0) | // mark word
     *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397) // 类型指针
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * @Author yangsj
     * @Date 2020/7/29 11:48
     **/
    @Test
    public void biasedLock() throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }



    /**
     * @Description 偏向锁，偏向具体锁持有线程 1 01
     * 占用 thread 的位置的已经有值了
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           05 38 12 03 (00000101 00111000 00010010 00000011) (51525637)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * @Author yangsj
     * @Date 2020/7/29 11:51
     **/
    @Test
    public void biasedLock2() throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }


    /**
     * @Description 轻量级锁 00
     * 子线程首先加锁，待子线程退出后，主线程尝试加锁
     * @Author yangsj
     * @Date 2020/7/29 15:27
     **/
    @Test
    public void LightWeightLock() throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        /** thread 持有偏向锁
         *thread locking...
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           05 a0 e0 19 (00000101 10100000 11100000 00011001) (434151429)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         */
        Thread thread = new Thread(()->{
            synchronized (o){
                System.out.println("thread locking...");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());//偏向锁
            }
        });

        thread.start();
        thread.join();

        /** 子线程持有锁并退出后，锁对象仍为偏向模式，此时主线程来获取锁，会升级偏向锁为轻量级锁
         * main locking...
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           c0 e5 51 02 (11000000 11100101 01010001 00000010) (38921664)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
        synchronized (o){
            System.out.println("main locking...");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());//轻量级锁
        }
    }


    /**
     * @Description  线程存在锁竞争的情况下，升级为重量级锁
     *
     * 在一个线程持有锁的过程中，另一个线程尝试获取锁
     *
     * 线程A locking ...
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           ba f6 9e 17 (10111010 11110110 10011110 00010111) (396293818)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * 线程B locking ...
     * java.lang.Object object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           ba f6 9e 17 (10111010 11110110 10011110 00010111) (396293818)
     *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     *       8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     *
     * @Author yangsj
     * @Date 2020/7/29 14:48
     **/
    @Test
    public void HeavyWeightLock() throws InterruptedException {
        Object o = new Object();
        Thread threadA = new Thread(()->{
            synchronized (o){
                System.out.println("线程A locking ...");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(()->{
            synchronized (o){
                System.out.println("线程B locking ...");
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

    }
}
