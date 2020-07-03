package com.nchu.api.netty.rpc.provider;

import com.nchu.api.netty.rpc.common.RpcService;

/**
 * @Decription 服务提供方，提供服务实现
 * @Author yangsj
 * @Date 2020/7/3 10:55
 **/
public class RpcServiceImpl implements RpcService {
    @Override
    public String rpcQuest(String msg) {
        System.out.println("收到消费方发送的信息：{"+msg+"}");
        return "你好消费者，我已经收到你的消息：{"+msg+"}";
    }
}
