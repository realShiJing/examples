package com.nchu.dubbo.rpc;

import com.nchu.dubbo.rpc.service.HelloService;
import com.nchu.dubbo.rpc.service.HelloServiceImpl;

/**
 * RpcProvider 暴露服务
 *
 * @author william.liangf
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }

}
