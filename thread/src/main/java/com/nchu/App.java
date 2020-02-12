package com.nchu;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程相关
 */
public class App{
    /**
     * @Description  CAS 测试
     * @Author yangsj
     * @Date 2020/2/12 8:48 下午
     **/
    @Test
    public void test(){
        AtomicInteger automicInteger = new AtomicInteger(0);

        automicInteger.getAndIncrement();

        boolean b = automicInteger.compareAndSet(1, 2);
        System.out.println(b);
    }

    /**
     * ArrayList 非线程安全，多线程环境下会出现 ConcurrentModificationException 运行时异常
     */
    @Test
    public void test1(){
        // ArrayList<Integer> list = new ArrayList();
        // Vector 能保证线程安全，但是性能低
        // Vector<Integer> list = new Vector<>();
        // List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        // CopyOnWriteArrayList 写时复制，写操作加锁，读操作不加锁，读写分离；适合读多写少
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                list.add(finalI);
            }).start();
        }
        System.out.println(list);

        // CopyOnWriteArrayList 迭代器不支持删除，会抛出 UnsupportedOperationException 异常

       /* Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                if(iterator.hasNext()){
                    iterator.remove();
                }
            }).start();
        }*/

    }

    /**
     * Collections.synchronizedList() 包装下的 list 迭代器组合操作同样存在原子性问题
     * 此时需要加锁 synchronized(list)
     */
    @Test
    public void test2() throws InterruptedException {
         List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                list.add(finalI);
            }).start();
        }
        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                synchronized (list){
                    if(iterator.hasNext()){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 在未加 synchronized (list) 的情况下可能出现遍历到同一个元素
                        System.out.println(iterator.next());
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
    }
}
