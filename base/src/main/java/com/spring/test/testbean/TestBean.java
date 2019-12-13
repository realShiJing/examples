package com.spring.test.testbean;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/11 10:57
 **/
public class TestBean {
    private  String name;

    private int age;

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

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
