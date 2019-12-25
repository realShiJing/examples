package com.nchu.base.noifelse.strategy;

import org.junit.Test;

/**
 * @Decription 策略模式替换 is else 测试
 * @Author yangsj
 * @Date 2019/12/25 15:07
 **/
public class App {
    @Test
    public void test(){
        OrderAdminRole orderAdminRole = new OrderAdminRole("ROLE_ORDER_ADMIN");
        // 想策略上下文中传入策略
        RoleContext context = new RoleContext(orderAdminRole);
        // 执行策略
        String returnStr = context.execute();
        System.out.println(returnStr);
    }
}
