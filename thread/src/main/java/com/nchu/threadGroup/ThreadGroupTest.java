package com.nchu.threadGroup;

import org.junit.Test;

import java.util.stream.IntStream;


/**
 * @Description 线程优先级和线程组相关测试
 *
 * 线程组是一个树状的结构，每个线程组下面可以有多个线程或者线程组。线程组可以起到统一控制线程的优先级和检查线程的权限的作用。
 *
 * @Author yangsj
 * @Date 2020/1/10 16:02
 **/
public class ThreadGroupTest {


    /**
     * @Description 线程组测试
     * @Author yangsj
     * @Date 2020/1/10 14:56
     **/
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("当前线程的的线程组为：" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("当前线程为："+ Thread.currentThread().getName());
        });
        thread.start();
        System.out.println("执行 main 方法的线程为：" + Thread.currentThread().getName());
    }


    /**
     * @Description 线程优先级
     * @Author yangsj
     * @Date 2020/1/10 15:15
     **/
    @Test
    public void threadPriority(){
        Thread threadA = new Thread();
        System.out.println("默认的线程优先级 "+ threadA.getPriority() );

        Thread threadB = new Thread();
        threadB.setPriority(10);
        System.out.println("修改的线程优先级 "+ threadA.getPriority() );
    }


    /**
     * @Description 测试线程优先级
     * 线程优先级高的并不一定会先运行
     * Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。而真正的调用顺序，是由操作系统的线程调度算法决定的。
     * @Author yangsj
     * @Date 2020/1/10 15:36
     **/
    @Test
    public void testPriority() throws InterruptedException {

        IntStream.range(1,10).forEach(i->{
            Thread thread = new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ ": " + Thread.currentThread().getPriority());
            });
            thread.setPriority(i);
            thread.start();
        });

        Thread.sleep(2000);
    }


    /**
     * @Description 线程优先级大于测试线程组优先级的情况
     * 
     * 如果某个线程优先级大于线程所在线程组的最大优先级，那么该线程的优先级将会失效，取而代之的是线程组的最大优先级。
     *
     * @Author yangsj
     * @Date 2020/1/10 15:50
     **/
    @Test
    public void testThreadGroup(){
        // 线程组
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        // 线程
        Thread thread = new Thread(threadGroup,"thread");

        // 设置线程组的优先级
        threadGroup.setMaxPriority(5);;
        // 设置线程的优先级
        thread.setPriority(10);

        thread.start();

        System.out.println("线程的优先级：" + thread.getPriority());
        System.out.println("线程组的优先级："+threadGroup.getMaxPriority());
    }

    /**
     * @Description 测试线程组复制
     * @Author yangsj
     * @Date 2020/1/10 16:08
     **/
    @Test
    public void testGroupCopy(){
        // 线程组
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        // 复制一个线程数组到一个新的线程组
        Thread[] threads = new Thread[threadGroup.activeGroupCount()];

        // 新的线程组
        ThreadGroup newThreadGroup = new ThreadGroup("new");

        newThreadGroup.enumerate(threads);
    }


    /**
     * @Description 测试由线程组统一处理异常
     * @Author yangsj
     * @Date 2020/1/10 16:13
     **/
    @Test
    public void testGroupException(){
        ThreadGroup threadGroup = new ThreadGroup("threadGroup"){
            // 匿名内部类继承 ThreadGroup 并重新定义以下方法
            // 在线程成员抛出 unchecked exception 时会执行该方法
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+":"+e.getMessage());
            }
        };

        Thread thread = new Thread(threadGroup,()->{
            throw new RuntimeException("测试异常");
        });

        thread.start();
    }
}
