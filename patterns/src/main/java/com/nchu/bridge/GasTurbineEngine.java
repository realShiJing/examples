package com.nchu.bridge;

/**
 * @Decription 燃气涡轮发动机(ConcreteImplementor : 具体实现类)
 * @Author yangsj
 * @Date 20191209 15:21
 **/
public class GasTurbineEngine implements Engine {
    @Override
    public void launch() {
        System.out.println("燃气涡轮发动机已发动");
    }
}
