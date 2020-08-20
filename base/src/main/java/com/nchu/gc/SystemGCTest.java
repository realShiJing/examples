package com.nchu.gc;

/**
 * @Decription System.gc() 测试
 * 经验证，手动调用System.gc()并不一定会出发虚拟机的垃圾回收
 * @Author yangsj
 * @Date 2020/8/20 11:06
 **/
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        System.gc();//提醒jvm的垃圾回收器执行gc,但是不确定是否马上执行gc
        //与Runtime.getRuntime().gc()的作用一样。
        //System.runFinalization();//强制调用 失去引用的对象的finalize()方法
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGCTest 重写了finalize()");
    }
}
