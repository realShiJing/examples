package com.nchu.base.noifelse.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Decription 角色工厂
 *
 * 这样的话以后想扩展条件也很容易，只需要增加新代码，而不需要动以前的业务代码，非常符合“开闭原则”
 * @Author yangsj
 * @Date 2019/12/25 14:14
 **/
public class RoleFactory {
    // 本地缓存存储各个角色
    static Map<String, Role> roleMap = new HashMap<>();

    static {
        roleMap.put("ROLE_ROOT_ADMIN",new RootAdminRole("ROLE_ROOT_ADMIN"));
        roleMap.put("ROLE_ORDER_ADMIN", new OrderAdminRole("ROLE_ORDER_ADMIN"));
        roleMap.put("ROLE_NORMAL", new NormalRole("ROLE_NORMAL"));
    }

    /**
     * 静态工厂方法获取角色
     * @param roleName
     * @return
     */
    public static Role getOp(String roleName){
        return roleMap.get(roleName);
    }
}
