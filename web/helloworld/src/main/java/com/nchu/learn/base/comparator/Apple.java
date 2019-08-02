package com.nchu.learn.base.comparator;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 20190802 11:49
 **/
public class Apple {
    //重量大小
    public int weight;
    //颜色 (红色，绿色，黄色)
    public String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
