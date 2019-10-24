package com.nchu.prototype;

import java.io.Serializable;

/**
 * @Decription 原型模式
 * @Author yangsj
 * @Date 20191024 9:34
 **/
public class Person implements Cloneable,Serializable{
    //姓名
    private String name;
    //年龄
    private int age;
    //性别
    private String sex;
    //宠物（引用类型）
    private Animal animal;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", animal=" + animal +
                '}';
    }

    /**
     * @Description 实现Object克隆方法（浅拷贝）
     * @Author yangsj
     * @Date 2019/10/24 9:43
     **/
    @Override
    protected Object clone(){
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }

    }
}
