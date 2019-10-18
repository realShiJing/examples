package com.nchu.factory.simple;

/**
 * @Decription 简单工厂（静态工厂方法）：
 * 定义一个静态方法，用于返回需要创建的对象
 * @Author yangsj
 * @Date 20191018 9:21
 **/
public class FruitFactory {

    /**
     * @Description 创建对象实例
     * @Author yangsj
     * @Date 2019/10/18 9:23
     **/
    public static Fruit getFruit(String fruitName){
        //反射机制创建实例
        try {
            StringBuffer packageName = new StringBuffer("com.nchu.factory.simple.");
            Class fruit = Class.forName(String.valueOf(packageName.append(fruitName)));
            return (Fruit) fruit.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("参数异常，找不到类:"+e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("非法访问:"+e.getMessage());
        } catch (InstantiationException e) {
            System.out.println("无法创建接口或者抽象类的实例："+ e.getMessage());
        }
        return null;
    }
}
