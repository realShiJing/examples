package com.nchu.bridge;

/**
 * @Decription RefinedAbstraction：扩充抽象类
 * @Author yangsj
 * @Date 20191209 15:17
 **/
public class Truck extends Car {

    Truck(Engine engine) {
        super(engine);
    }

    /**
     * 启动卡车
     */
    @Override
    public void start() {
        System.out.print("卡车：");
        engine.launch();
    }
}
