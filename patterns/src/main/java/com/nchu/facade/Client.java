package com.nchu.facade;

/**
 * @Decription 客户端 测试外观模式
 * @Author yangsj
 * @Date 20191125 17:06
 **/
public class Client {
    public static void main(String[] args) {
        //声明外观角色
        Facade facade = new Facade();
        facade.wrapOperation1();
        System.out.println("----------------------------");
        facade.wrapOperation2();

    }
}
