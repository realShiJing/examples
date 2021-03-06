package com.example.cloud.zookeeper.service.impl;


import com.example.cloud.zookeeper.service.ProviderService;
import com.nchu.cloud.vo.ResultVo;
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
    public ResultVo<String> getInfo(String id) {
        ResultVo<String> resultVo = new ResultVo<>();
        resultVo.setCode(0000);
        resultVo.setData(id+"-Hello Zookeeper");
        resultVo.setMessage(" success! serverPort:"+serverPort);
        return resultVo;
    }
}
