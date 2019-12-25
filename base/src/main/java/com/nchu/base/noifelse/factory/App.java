package com.nchu.base.noifelse.factory;

import org.junit.Test;

/**
 * @Decription 测试
 * @Author yangsj
 * @Date 2019/12/25 14:35
 **/
public class App {
    /**
     * 通过工厂模式替换 if else 操作
     */
    @Test
    public void test(){
        Role role_normal = RoleFactory.getOp("ROLE_NORMAL");
        System.out.println(role_normal.op());
    }
}
