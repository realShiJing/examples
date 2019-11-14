package com.nchu.flyweight;

/**
 * @Decription 具体享元角色
 * @Author yangsj
 * @Date 20191114 10:29
 **/
public class ConcreteFlyWeight implements FlyWeight{
    //标识 内部状态
    public String intrinsicState;


    ConcreteFlyWeight(String intrinsicState){
        this.intrinsicState = intrinsicState;
    }


    /**
     * @Description 外部操作
     * 外部状态，由调用者指定，同一个内部状态对应着一个对象，同一个对象可以有不同的外部状态
     * @Author yangsj
     * @Date 2019/11/14 11:12
     **/
    @Override
    public void doOperation(String extriniscState) {
        System.out.println("ConcreteFlayWeight :" + intrinsicState + " \t DoOperation :" + extriniscState);
    }
}
