package com.nchu.bridge;

/**
 * @Decription 活塞发动机(ConcreteImplementor : 具体实现类)
 * @Author yangsj
 * @Date 20191209 15:23
 **/
public class PistonEngine implements Engine {

    @Override
    public void launch() {
        System.out.println("活塞发动机已启动");
    }
}
