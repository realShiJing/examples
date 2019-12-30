package com.nchu.templatemethod;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/27 16:56
 **/
public class Truck extends Car {
    @Override
    public void makeHead() {
        System.out.println("truck:组装车头");
    }

    @Override
    public void makeBody() {
        System.out.println("truck:组装车身");
    }

    @Override
    public void makeTail() {
        System.out.println("truck:组装车尾");
    }
}
