package com.nchu.cloud.eureka.service.impl;

import com.nchu.cloud.eureka.service.ProviderService;
import com.nchu.cloud.vo.ResultVo;
import org.springframework.stereotype.Service;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 14:40
 **/
@Service
public class ProviderServiceImpl implements ProviderService {
    @Override
    public ResultVo<String> getInfo(String id) {
        ResultVo<String> resultVo = new ResultVo<>();
        resultVo.setCode(0000);
        resultVo.setData(id+"Hello Eureka");
        resultVo.setMessage("success!");
        return resultVo;
    }
}
