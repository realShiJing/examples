package com.nchu.cloud.eureka.hystrix.service;

public interface ProviderService {

    /**
     * @Description 正常请求
     * @Author yangsj
     * @Date 2020/5/14 9:53
     **/
    String getInfo(Integer id);


    /**
     * @Description 超时请求
     * @Author yangsj
     * @Date 2020/5/14 9:54
     **/
    String getTimeOut(Integer id);


    /**
     * @Description 服务熔断
     * @Author yangsj
     * @Date 2020/5/14 14:50
     **/
    String circuitBreaker(Integer id);

}
