package com.nchu.strategy;

/**
 * @Decription 封装类
 * @Author yangsj
 * @Date 20191106 12:00
 **/
public class Context {

    //抽象策略，具体策略由客户端指定
    public Strategy strategy;


    /**
     * @Description 构造方法，传入具体策略
     * @Author yangsj
     * @Date 2019/11/6 13:52
     **/
    Context(Strategy strategy){
        this.strategy = strategy;
    }


    /**
     * @Description 调用具体策略的算法
     * @Author yangsj
     * @Date 2019/11/6 13:53
     **/
    public void encrypt(){
        strategy.encrypt();
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
}
