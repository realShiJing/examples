package com.nchu.prototype;

import java.io.Serializable;

/**
 * @Decription 宠物
 * @Author yangsj
 * @Date 20191024 11:03
 **/
public class Animal implements Serializable {

    //宠物名
    private String name;
    //宠物种类
    private String kind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
