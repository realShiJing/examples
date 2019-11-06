package com.nchu.strategy;

/**
 * @Decription MD5加密 （具体策略）
 * @Author yangsj
 * @Date 20191106 11:57
 **/
public class MD5Strategy implements Strategy {
    @Override
    public void encrypt() {
        System.out.println("MD5加密");
    }
}
