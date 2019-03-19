package com.nchu.builder;

/**
 * @description:  公寓建造者
 * @auther: yangsj
 * @created: 2019/3/19 19:47
 */
public class ApartmentBuilder implements HouseBuilder {
    House house = new House();
    @Override
    public void makeFloor() {
        house.setFloor("公寓————>地板");
    }

    @Override
    public void makeWall() {
        house.setWall("公寓————>围墙");
    }

    @Override
    public void makeHousetop() {
        house.setHousetop("公寓————>房顶");
    }

    @Override
    public House getHouse() {
        return house;
    }
}
