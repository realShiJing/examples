package com.nchu.builder;

/**
 * @description: 导演类
 * @auther: yangsj
 * @created: 2019/3/19 19:52
 */
public class HouseDirector {
    /**
     *指导建造者创建产品
     */
    public void makeHouse(HouseBuilder builder) {
        builder.makeFloor();
        builder.makeWall();
        builder.makeHousetop();
    }
}
