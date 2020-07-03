package com.nchu.api.netty.rpc.consumer;

import com.nchu.api.netty.rpc.common.RpcService;

/**
 * @Decription 远程服务调用入口
 * @Author yangsj
 * @Date 2020/7/3 11:06
 **/
public class App {
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        // 获取远程代理对象
        RpcService rpcService = (RpcService) client.getBean(RpcService.class);
        for (int i = 0; i < 10; i++) {
            String result = rpcService.rpcQuest("你好！");
            System.out.println("远程调用返回结果："+result);
            System.out.println("========================================");
        }

    }
}
