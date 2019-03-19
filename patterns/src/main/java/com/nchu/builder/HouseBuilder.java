package com.nchu.builder;

/**
 *@description:  抽象建造者 建造产品，返回产品
 *@auther: yangsj
 *@created: 2019/3/19 19:43
 *
 */
public interface HouseBuilder {
    //修地板
    void makeFloor();
    //修墙
    void makeWall();
    //修屋顶
    void makeHousetop();
    //返回产品
    House getHouse();
}
