package com.example.cloud.eureka.controller;

import com.example.cloud.eureka.loadbalance.LoadBalancer;
import com.nchu.cloud.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 15:44
 **/
@RestController
public class ConsumerController {
    // 直连调用
    //public static final String PROVIDER_URL = "http://127.0.0.1:8002";
    // 通过在eureka上注册过的微服务名称调用
    public static final String PROVIDER_URL = "http://CLOUD-EUREKA-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 自定义自定义负载均衡器
     */
    @Autowired
    private LoadBalancer loadBalancer;


    /**
     * @Description 通过 HTTP 请求远程调用服务
     * @Author yangsj
     * @Date 2020/5/9 16:39
     **/
    @GetMapping("/consumer/provider/getInfo/{id}")
    public ResultVo getInfo(@PathVariable("id")String id){
        return restTemplate.getForObject(PROVIDER_URL + "/provider/getInfo/" + id, ResultVo.class);
    }


    /**
     * @Description 获取服务提供者的信息
     * @Author yangsj
     * @Date 2020/5/9 16:39
     **/
    @GetMapping("/consumer/discovery")
    public ResultVo getProviderUrl(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-EUREKA-PROVIDER");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        return  ResultVo.success(instances);
    }


    /**
     * @Description 自定义负载均衡规则，测试时需去除 RestTemplate 上的  @LoadBalanced 注解
     * @Author yangsj
     * @Date 2020/5/13 11:13
     **/
    @GetMapping("/consumer/getInfoBySfLoadBalace/{id}")
    public ResultVo getInfoBySfLoadBalace(@PathVariable("id")String id){
        // 通过服务名从注册中心发现服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-EUREKA-PROVIDER");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        // 根据自定义规则获取服务
        ServiceInstance serviceInstance = loadBalancer.getService(instances);
        // 获取服务的 uri ，并发送请求
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(PROVIDER_URL + "/provider/getInfo/" + id, ResultVo.class);
    }
    
}
