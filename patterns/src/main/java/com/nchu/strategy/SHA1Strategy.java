package com.nchu.strategy;

/**
 * @Decription SHA1算法（具体策略）
 * @Author yangsj
 * @Date 20191106 11:58
 **/
public class SHA1Strategy implements Strategy {
    @Override
    public void encrypt() {
        System.out.println("SHA1加密");
    }
}
