package com.nchu.cloud.eureka.hystrix.controller;

import com.nchu.cloud.eureka.hystrix.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription 服务调用
 * @Author yangsj
 * @Date 2020/5/14 11:56
 **/
@RestController
/*@DefaultProperties(defaultFallback = "globalFallbackMethod")*/ //全局兜底方法
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    /**
     * @Description 正常访问
     * @Author yangsj
     * @Date 2020/5/14 11:55
     **/
    @GetMapping("/consumer/provider/getInfo/{id}")
    String getInfo(@PathVariable("id") Integer id){
        return consumerService.getInfo(id);
    }


    /**--------------  服务调用者 服务降级 begin---------------------**/
    /**
     * @Description 超时访问
     * @Author yangsj
     * @Date 2020/5/14 11:55
     **/
    /*@HystrixCommand(fallbackMethod = "timeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")
    })*/ // 精确 fallback，针对特定方法
   // @HystrixCommand //全局 fallback
    @GetMapping("/consumer/provider/getTimeOut/{id}")
    String getTimeOut(@PathVariable("id") Integer id){
        return consumerService.getTimeOut(id);
    }

    /**
     * @Description 超时方法fallback
     * @Author yangsj
     * @Date 2020/5/14 14:15
     **/
    public String timeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方系统繁忙请10秒种后再试或者自己运行出错请检查自己！";
    }

    /**
     * @Description 全局 fallback
     * 无法传参
     * @Author yangsj
     * @Date 2020/5/14 14:15
     **/
    public String globalFallbackMethod() {
        return "Global异常处理信息,请稍后重试！";
    }

    /**--------------  服务调用者 服务降级 end---------------------**/
    @HystrixCommand //调用有服务熔断服务的服务
    @GetMapping("/consumer/provider/circuit/{id}")
    String circuitBreaker(@PathVariable("id") Integer id){
        return consumerService.circuitBreaker(id);
    }
    public String circuitBreakerFallback(Integer id){
        return "id 不能负数,请稍后重试! " + id;
    }
}
