package com.nchu.cloud.eureka.loadbalance.impl;

import com.nchu.cloud.eureka.loadbalance.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Decription 自定义 Ribbon 轮询算法
 * @Author yangsj
 * @Date 2020/5/13 10:14
 **/
@Component
public class LoadBalancerImpl implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * @Description 轮训从 eureka 注册容器服务列表中 轮询获取服务
     * @Author yangsj
     * @Date 2020/5/13 10:16
     **/
    @Override
    public ServiceInstance getService(List<ServiceInstance> serviceInstances) {
        int count = atomicInteger.getAndIncrement();
        // 通过取余实现轮询
        int index = count % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
