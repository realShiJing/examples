package com.nchu.prototype;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription 原型模式相关测试
 * @Author yangsj
 * @Date 20191024 9:36
 **/
public class App {

    /**
     * @Description
     * @Author yangsj
     * @Date 2019/10/24 9:41
     **/
    @Test
    public void test1(){
        //创建主人
        Person person = new Person();
        person.setName("yangsj");
        person.setAge(24);
        person.setSex("男");
        //创建宠物
        Animal animal = new Animal();
        animal.setName("贝贝");
        animal.setKind("dog");
        //认领宠物
        person.setAnimal(animal);

        //浅拷贝克隆对象
        Person clone =(Person)person.clone();

        System.out.println("----------浅拷贝-----------");
        System.out.println("原型对象的hashCode : "+person.hashCode());
        System.out.println("克隆对象的hashCode : "+clone.hashCode());

        System.out.println("浅拷贝，引用类型地址是否相同："+(person.getAnimal() == clone.getAnimal()));

        //深拷贝克隆对象
        Person clone1 = CloneUtil.clone(person);

        System.out.println("----------深拷贝-----------");
        System.out.println("原型对象的hashCode : "+person.hashCode());
        System.out.println("克隆对象的hashCode : "+clone1.hashCode());

        System.out.println("深拷贝，引用类型地址是否相同："+(person.getAnimal() == clone1.getAnimal()));
    }


    /**
     * @Description Spring原型模式创建对象
     * @Author yangsj
     * @Date 2019/10/24 17:29
     **/
    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        Person person = (Person) applicationContext.getBean("id01");
        Person person1 = (Person) applicationContext.getBean("id01");
        System.out.println(person.hashCode());
        System.out.println(person1.hashCode());
        System.out.println("通过Spring原型模式创建的对象是否相同：" + (person == person1));
        System.out.println("通过Spring原型模式创建的对象是否相同："+(person.getAnimal() == person1.getAnimal()));


    }
}
