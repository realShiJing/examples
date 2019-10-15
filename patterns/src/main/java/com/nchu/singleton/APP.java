package com.nchu.singleton;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/20 10:47
 */
public class APP {
    public PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
    @Test
    public void  Test() throws IOException {
        String username = propertiesUtils.getProperty("username");
        System.out.print(username);
    }


    /**
     * @Description 单例模式测试
     * @Author yangsj
     * @Date 2019/10/15 16:12
     **/
    @Test
    public void test1(){
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        System.out.println(instance1 == instance2);
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }


    /**
     * @Description 多线程测试
     * @Author yangsj
     * @Date 2019/10/15 16:18
     **/
    @Test
    public void threadTest() throws InterruptedException {
        int count = 0;
        int size = 100000;
        Singleton3[] instances= new Singleton3[size];
        CountDownLatch latch = new CountDownLatch(size);
        while(count < size  ){
            int i = count;
            new Thread(() -> {
                instances[i] = Singleton3.getInstance();
                latch.countDown();
            }).start();
            count++;
        }
        latch.await();
        for (int i = 0; i < instances.length; i++) {
            System.out.println(instances[i].hashCode());
        }
    }


}
