package com.nchu.templatemethod;

import org.junit.Test;

/**
 * @Decription 模板方法模式测试
 * @Author yangsj
 * @Date 2019/12/27 16:57
 **/
public class App {
    @Test
    public void test(){
        Car bus = new Bus();
        //调用模板方法制造公交车
        bus.make();

        Car truck = new Truck();
        //制造卡车
        truck.make();
    }
}
