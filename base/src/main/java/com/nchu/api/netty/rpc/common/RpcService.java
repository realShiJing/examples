package com.nchu.api.netty.rpc.common;


/**
 * @Description 服务提供者和消费者 协议接口信息
 * @Author yangsj
 * @Date 2020/7/3 10:51
 **/
public interface RpcService {
    String rpcQuest(String msg);
}
