package com.nchu.builder;

/**
 * @description: 房子 产品类
 * @auther: yangsj
 * @created: 2019/3/19 19:41
 */
public class House {

    /**
     * 地板，此应该为另一个对象的引用，为了更好理解建造者模式，这里简化为String类型
     */
    private String floor;
    // 墙
    private String wall;
    // 屋顶
    private String housetop;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getHousetop() {
        return housetop;
    }

    public void setHousetop(String housetop) {
        this.housetop = housetop;
    }

}
