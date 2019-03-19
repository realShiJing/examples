package com.nchu.builder;

import org.junit.Test;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/19 19:52
 */
public class APP {
    @Test
    public void Test(){
        //创建指导者
        HouseDirector houseDirector = new HouseDirector();

        //创建建造者
        ApartmentBuilder apartmentBuilder = new ApartmentBuilder();

        //指导者 指导建造者 建造房子
        houseDirector.makeHouse(apartmentBuilder);

        //生成房子
        House house = apartmentBuilder.getHouse();

        System.out.println(house.getFloor() +"\n"+ house.getWall() +"\n"+ house.getHousetop());
    }
}
