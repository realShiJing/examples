package com.nchu.base.noifelse.factory;

/**
 * @Decription 系统管理员角色
 * @Author yangsj
 * @Date 2019/12/25 14:05
 **/
public class RootAdminRole implements Role {

    private String roleName;

    public RootAdminRole(String roleName){
        this.roleName = roleName;
    }
    @Override
    public String op() {
        return roleName + "has A permission";
    }
}
