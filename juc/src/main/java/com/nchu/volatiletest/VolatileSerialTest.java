package com.nchu.volatiletest;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: volatile 有序性测试
 *
 * as-if-serial 语义
 *      as-if-serial语义的意思是：不管怎么重排序（编译器和处理器为了提高并行度），单线程程序的执行结果不能被改变。
 * 编译器、runtime 和处理器都必须遵守as-if-serial语义。
 *      为了遵守as-if-serial语义，编译器和处理器 不会对存在数据依赖关系的操作做重排序，因为这种重排序会改变执行结果。
 * 但是，如果操作之间不存在数据依赖关系，这些操作就可能被编译器和处理器重排序。
 *
 * happens-before 原则 ：前一个操作的执行结果对后一个操作可见的原则
 *
 * hotspot虚拟机C++源码 是使用 StoreStore、LoadLoad、StoreLoad、LoadStore内存屏障
 * 最终执行的汇编指令是 给vloatile 修饰的变量加上 lock指令前缀
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/21 10:29
 **/
public class VolatileSerialTest {
    static int x = 0 , y = 0 ;
    static volatile int a = 0 , b = 0 ;

    /**
     * 由于指令重排序，最终结果有四种
     * [a=0,b=0, a=1,b=0, a=0,b=1, a=1,b=1]
     *
     * 给 a b 加上 volatile 关键字后，就不会出现 a = 1 ,b = 1这种情况
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(()->{
                a = y;//2
                x = 1;//3
            });

            Thread other = new Thread(()->{
                b = x;//4
                y = 1;//1
            });

            one.start();
            other.start();

            one.join();
            other.join();

            resultSet.add("a=" + a + ",b=" + b);

            System.out.println(resultSet);
        }
    }
}
