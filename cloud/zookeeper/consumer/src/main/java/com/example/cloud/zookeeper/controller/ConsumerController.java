package com.example.cloud.zookeeper.controller;

import com.nchu.cloud.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    // 注意区分大小写
    public static final String PROVIDER_URL = "http://cloud-zookeeper-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


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
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-zookeeper-provider");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        return  ResultVo.success(instances);
    }
}
