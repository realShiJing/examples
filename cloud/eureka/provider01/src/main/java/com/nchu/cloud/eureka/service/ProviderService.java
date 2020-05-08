package com.nchu.cloud.eureka.service;


import com.nchu.cloud.vo.ResultVo;

public interface ProviderService {
    ResultVo<String> getInfo(String id);
}
