package com.nchu.templatemethod;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/17 20:01
 **/
public abstract class Car {
    /**
     * 组装车头
     */
    public abstract void makeHead();
    /**
     * 组装车身
     */
    public abstract void makeBody();

    /**
     * 组装车尾
     */
    public abstract void makeTail();

    /**
     * 组装完整的车
     */
    public void make(){
        makeHead();
        makeBody();
        makeTail();
    }
}
