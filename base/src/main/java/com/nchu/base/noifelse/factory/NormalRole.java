package com.nchu.base.noifelse.factory;

/**
 * @Decription 普通用户角色
 * @Author yangsj
 * @Date 2019/12/25 14:10
 **/
public class NormalRole implements Role {
    private String roleName;

    public NormalRole(String roleName){
        this.roleName = roleName;
    }
    @Override
    public String op() {
        return roleName + "has C permission";
    }
}
