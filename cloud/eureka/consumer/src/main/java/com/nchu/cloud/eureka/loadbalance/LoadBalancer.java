package com.nchu.cloud.eureka.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;


/**
 * @Description 自定义负载均衡器
 * @Author yangsj
 * @Date 2020/5/13 11:17
 **/
public interface LoadBalancer {

    /**
     * @Description 根据负载均衡规则获取服务
     * @Author yangsj
     * @Date 2020/5/13 10:16
     **/
    ServiceInstance getService(List<ServiceInstance> serviceInstances);
}
