package com.nchu.base.noifelse.factory;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/25 14:09
 **/
public class OrderAdminRole implements Role {

    private String roleName;

    public OrderAdminRole(String roleName){
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + "has B permission";
    }
}
