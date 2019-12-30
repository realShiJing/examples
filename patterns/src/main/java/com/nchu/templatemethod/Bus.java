package com.nchu.templatemethod;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/17 20:04
 **/
public class Bus extends Car{
    @Override
    public void makeHead() {
        System.out.println("bus:组装车头");
    }

    @Override
    public void makeBody() {
        System.out.println("bus:组装车身");
    }

    @Override
    public void makeTail() {
        System.out.println("bus:组装车尾");
    }
}
