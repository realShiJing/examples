package com.nchu.facade;

/**
 * @Decription 外观角色
 * @Author yangsj
 * @Date 20191125 17:00
 **/
public class Facade {
    //将子系统聚合到外观角色中
    private SystemA systemA;
    private SystemB systemB;
    private SystemC systemC;


    /**
     * @Description 在构造方法中初始化 子系统
     * @Author yangsj
     * @Date 2019/11/25 17:03
     **/
    Facade(){
        systemA = new SystemA();
        systemB = new SystemB();
        systemC = new SystemC();
    }

    /**
     * @Description 向客户端暴露的子系统组合操作方法
     * @Author yangsj
     * @Date 2019/11/25 17:05
     **/
    public void wrapOperation1(){
        System.out.println("组合操作1");
        systemA.operationA();
        systemB.operationB();
        systemC.operationC();
    }

    public void wrapOperation2(){
        System.out.println("组合操作2");
        systemB.operationB();
        systemC.operationC();
    }
}
