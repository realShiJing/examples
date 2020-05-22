package com.nchu.cloudalibaba.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/22 10:55
 **/
public class CustomerBlockHandler {


    /**
     * @Description 必须是 static 修饰
     * @Author yangsj
     * @Date 2020/5/22 10:56
     **/
    public static String handlerException(BlockException exception){
         return "按客戶自定义,global handlerException";
    }
}
