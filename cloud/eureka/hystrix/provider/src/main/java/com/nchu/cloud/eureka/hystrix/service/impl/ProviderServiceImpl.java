package com.nchu.cloud.eureka.hystrix.service.impl;

import com.nchu.cloud.eureka.hystrix.service.ProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 14:40
 **/
@Service
public class ProviderServiceImpl implements ProviderService {
    @Value("${server.port}")
    private String serverPort;

    @Override
    public String getInfo(Integer id) {
        return "Info_OK>>线程池:" + Thread.currentThread().getName() +"，端口："+serverPort+ ",id:" + id ;
    }
    /*-------------------- 服务提供方的服务降级 begin ----------------------*/
    /**
     *
     * execution.isolation.thread.timeoutInMilliseconds:线程超时时间3秒钟
     *
     * HystrixCommand:一旦调用服务方法失败并抛出了错误信息后,或者调用超过了指定时间
     * 会自动调用@HystrixCommand标注好的fallbckMethod调用类中的指定方法
     */
    @Override
    @HystrixCommand(fallbackMethod = "timeOutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String getTimeOut(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能是负数");
        }
        int timeNumber = id * 1000;
       /* try {
            // 暂停5秒钟
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return "success>>线程池:" + Thread.currentThread().getName() +"，端口："+serverPort+  ",id:" + id + "\t" +
                "耗时(秒)" + timeNumber;
    }


    /**
     * @Description 超时兜底方法
     * @Author yangsj
     * @Date 2020/5/14 10:10
     **/
    public String timeOutHandler(Integer id) {
        return "fallback>>线程池:" + Thread.currentThread().getName() + " 系统繁忙或运行错误,请稍后重试,id:" + id;
    }

    /*-------------------- 服务降级 end ----------------------*/


    /**
     * @Description 服务熔断
     * 在10秒窗口期中10次请求有6次是请求失败的,断路器将起作用
     * @Author yangsj
     * @Date 2020/5/14 15:37
     **/
    @HystrixCommand(
            fallbackMethod = "circuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期/时间范文
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后熔断
    })
    public String circuitBreaker(Integer id){
        if (id < 0) {
            throw new RuntimeException("id不能是负数");
        }
        return Thread.currentThread().getName() + serverPort;
    }

    public String circuitBreakerFallback(Integer id){
        return "id 不能负数,请稍后重试! " + id;
    }
}
