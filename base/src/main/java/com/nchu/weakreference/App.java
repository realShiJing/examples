package com.nchu.weakreference;

import com.nchu.bean.Apple;
import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @Decription 弱引用相关测试
 * @Author yangsj
 * @Date 2020/4/10 16:42
 **/
public class App {


    /**
     * @Description 当一个对象仅仅被weak reference指向, 而没有任何其他strong reference指向的时候,
     * 如果GC运行, 那么这个对象就会被回收
     * @Author yangsj
     * @Date 2020/4/10 16:52
     **/
    @Test
    public void test1(){
        Apple apple = new Apple(12,"红色");
        WeakReference<Apple> weakReference = new WeakReference<>(apple);
        int i=0;
        // 即使有 apple 引用指向对象, 且 apple 是一个strong reference, weak reference weakReference指向的对象仍然被回收了.
        // 这是因为java的编译器在发现进入while循环之后, apple 已经没有被使用了, 所以进行了优化
        while (true){
//           如果此方法为空, 那么说明weakReference指向的对象已经被回收了.
            if(weakReference.get() != null ){
                i++;
                System.out.println("第"+i+"次循环中，对象还存活");
            }else {
                System.out.println("对象已被回收！");
                break;
            }
        }
    }
    /**
     * @Description
     * WeakReference的一个特点是它何时被回收是不可确定的, 因为这是由GC运行的不确定性所确定的.
     * soft reference和weak reference一样, 但被GC回收的时候需要多一个条件: 当系统内存不足时, soft reference指向的object才会被回收.
     * @Author yangsj
     * @Date 2020/4/10 16:52
     **/
    @Test
    public void test2(){
        Apple apple = new Apple(12,"红色");
        WeakReference<Apple> weakReference = new WeakReference<>(apple);
        int i=0;
        while (true){
            //weak reference指向的object就不会被回收了. 因为还有一个strong reference car 指向它.
            System.out.println("here is the strong reference 'apple' "+apple);
//           如果此方法为空, 那么说明weakReference指向的对象已经被回收了.
            if(weakReference.get() != null ){
                i++;
                System.out.println("第"+i+"次循环中，对象还存活");
            }else {
                System.out.println("对象已被回收！");
                break;
            }
        }
    }
}
