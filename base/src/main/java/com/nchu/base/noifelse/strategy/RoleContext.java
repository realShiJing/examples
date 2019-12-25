package com.nchu.base.noifelse.strategy;

/**
 * @Decription 策略上下文
 * @Author yangsj
 * @Date 2019/12/25 15:03
 **/
public class RoleContext {
    // 可更换的策略，传入不同的策略对象，业务即相应变化
    private  Role role;

    public RoleContext(Role role) {
        this.role = role;
    }
    public String execute(){
        return role.op();
    }

}
