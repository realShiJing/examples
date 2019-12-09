package com.nchu.bridge;

import org.junit.Test;

/**
 * @Decription 桥接模式 测试
 * @Author yangsj
 * @Date 20191209 15:26
 **/
public class App {
    @Test
    public  void  test(){
        // 涡轮发动机
        GasTurbineEngine gasTurbineEngine = new GasTurbineEngine();
        // 活塞发动机
        PistonEngine pistonEngine = new PistonEngine();

        // 装有涡轮发动机的 公共汽车
        Car bus1 = new Bus(gasTurbineEngine);
        // 装有活塞发动机的 公共汽车
        Car bus2 = new Bus(pistonEngine);

        // 装有涡轮发动机的 卡车
        Car truck1 = new Truck(gasTurbineEngine);
        // 装有活塞发动机的 卡车
        Car truck2 = new Truck(pistonEngine);

        bus1.start();
        bus2.start();

        truck1.start();
        truck2.start();
    }
}
