package com.nchu.base.noifelse.enumrole;

/**
 * 将不同角色的情况全部交由枚举类来做，定义一个不同角色有不同权限的枚举类 RoleEnum
 */
public enum RoleEnum implements Role {
    // 系统管理员(有A操作权限)
    ROLE_ROOT_ADMIN {
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN:"+" has A permission";
        }
    },
    // 订单管理员(有B操作权限)
    ROLE_ORDER_ADMIN {
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN:"+" has B  permission";
        }
    },
    // 普通用户(有C操作权限)
    ROLE_NORMAL  {
        @Override
        public String op() {
            return "ROLE_NORMAL :"+" has C  permission";
        }
    }
}
