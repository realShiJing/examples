package com.nchu.builder;

/**
 * @description: 酒店建造者
 * @auther: yangsj
 * @created: 2019/3/19 19:50
 */
public class HotelBuilder implements HouseBuilder {

    House house = new House();
    @Override
    public void makeFloor() {
        house.setFloor("酒店————>地板");
    }

    @Override
    public void makeWall() {
        house.setWall("酒店————>围墙");
    }

    @Override
    public void makeHousetop() {
        house.setHousetop("酒店————>房顶");
    }

    @Override
    public House getHouse() {
        return house;
    }
}
