package com.nchu.base.noifelse.enumrole;

import org.junit.Test;

/**
 * @Decription 用枚举替代 if/else 测试
 * @Author yangsj
 * @Date 2019/12/25 11:42
 **/
public class JudgeRole {
    /**
     * 根据用户名返回不同权限
     * @param roleName
     * @return
     */
    public String judge(String roleName){
        return RoleEnum.valueOf(roleName).op();
    }

    /**
     *  测试
     *
     * 后假如果想扩充条件，只需要去枚举类中加代码即可，而不是去改以前的代码
     *
     */
    @Test
    public void test(){
        String roleName = "ROLE_ROOT_ADMIN";
        String permission = judge(roleName);
        System.out.println(permission);
    }
}
