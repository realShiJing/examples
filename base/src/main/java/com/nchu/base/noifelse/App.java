package com.nchu.base.noifelse;

/**
 * @Decription 传统的 if else 模式
 * @Author yangsj
 * @Date 2019/12/25 15:13
 **/
public class App {

    /**
     *  1、不可读
     *  2、难扩展
     *  3、代码臃肿
     *
     * @param roleName
     * @return
     */
    public String judge(String roleName){
        String result = "";
        if (roleName.equals("ROLE_ROOT_ADMIN")) {
            result = "ROLE_ROOT_ADMIN has A permission";
        } else if (roleName.equals("ROLE_ORDER_ADMIN")) {
            result = "ROLE_ORDER_ADMIN has B permission";
        } else if (roleName.equals("ROLE_NORMAL")) {
            result = "ROLE_NORMAL has C permission";
        } else {
            result = "XXXXX";
        }
        return result;
    }
}
