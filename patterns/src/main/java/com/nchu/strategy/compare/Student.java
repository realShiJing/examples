package com.nchu.strategy.compare;

/**
 * @ClassName: Student
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 11:11
 **/
public class Student {
    private Integer id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name='" + name + "'}";
    }
}
