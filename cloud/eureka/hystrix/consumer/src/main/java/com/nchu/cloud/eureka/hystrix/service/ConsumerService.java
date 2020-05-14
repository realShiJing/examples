package com.nchu.cloud.eureka.hystrix.service;

import com.nchu.cloud.eureka.hystrix.service.fallback.ConsumerFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Decription 远程服务调用
 * 注解 @FeignClient 可以定义服务降级处理 service
 * 另外需在配置文件中配置feign.hystrix.enabled:true
 *
 * @Author yangsj
 * @Date 2020/5/14 11:52
 **/
@Component
@FeignClient(value = "CLOUD-HYSTRIX-PROVIDER",fallback = ConsumerFallBackService.class)
public interface ConsumerService {


    /**
     * @Description 远程调用服务提供方提供的 正常访问
     * @Author yangsj
     * @Date 2020/5/14 11:55
     **/
    @GetMapping("/provider/getInfo/{id}")
    String getInfo(@PathVariable("id") Integer id);



    /**
     * @Description 远程调用服务提供方提供的 超时访问
     * @Author yangsj
     * @Date 2020/5/14 11:55
     **/
    @GetMapping("/provider/getTimeOut/{id}")
    String getTimeOut(@PathVariable("id") Integer id);

    /**
     * @Description 远程调用服务提供方提供的 服务熔断
     * @Author yangsj
     * @Date 2020/5/14 11:55
     **/
    @GetMapping("/provider/circuit/{id}")
    String circuitBreaker(@PathVariable("id")Integer id);
}
