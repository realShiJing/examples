package com.nchu.allocator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 银行账户管理者(必须是单例)
 * @Author yangsj
 * @Date 2020/1/3 15:17
 **/
public class Allocator {
    // 内存可见，禁止指令重排
    private static volatile Allocator instance ;

    // 构造器私有化
    private Allocator(){}

    /**
     * 向外暴露一个静态的公共方法
     *
     * @Description  双重校验获取实例
     * @Author yangsj
     * @Date 2020-01-04 16:25
     **/
    public static Allocator getInstance(){
        if(instance == null){
            synchronized (Allocator.class){
                if(instance == null){
                     // 类的内部创建对象
                    instance = new Allocator();
                }
            }
        }
        return instance;
    }
    // 正在被占用处理的锁
    private List<Object> als = new ArrayList<>();

    /**
     * @Description  一次性申请所有锁
     *  true :申请成功
     *  false :申请失败
     * @Author yangsj
     * @Date 2020-01-04 16:33
     **/
    public synchronized void apply(Object from ,Object to){
        //缓存中包含资源，说明有线程还在操作这些资源
        while (als.contains(from) || als.contains(to)) {
            // 此时申请失败 ,阻塞当前线程，释放互斥锁
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //申请成功，标识资源未被占用，当前线程占用资源
        als.add(from);
        als.add(to);
    }

    /**
     * @Description 归还资源
     * @Author yangsj
     * @Date 2020-01-04 16:41
     **/
    public synchronized void free(Object from ,Object to){
        als.remove(from);
        als.remove(to);
        this.notifyAll();
    }

}
