package com.nchu.base.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @description:  SimpleDateFormat 线程安全性测试
 * @auther: yangsj
 * @created: 2019/5/9 9:35
 */
public class DateUtil {
    //private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static void main(String[] args) {
        //使用CountDownLatch 目的是主线程将子线程全部创建完后，再执行多线程的逻辑方法
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] strs = new String[] {"2016-01-01 10:24:00", "2016-01-02 20:48:00", "2016-01-11 12:24:00"};
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始执行多线程");
                for (int i1 = 0; i1 < 10; i1++){
                    try {
                        System.out.println(Thread.currentThread().getName()+ "\t" + parse(strs[i1 % strs.length]));
                        Thread.sleep(100);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("多线程全部创建完毕！！！");
        latch.countDown();
    }
}
