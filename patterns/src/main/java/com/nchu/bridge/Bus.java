package com.nchu.bridge;

/**
 * @Decription RefinedAbstraction：扩充抽象类
 * @Author yangsj
 * @Date 20191209 15:14
 **/
public class Bus extends Car {

    Bus(Engine engine) {
        super(engine);
    }

    /**
     * 启动Bus
     */
    @Override
    public void start() {
        System.out.print("公共汽车：");
        engine.launch();
    }
}
