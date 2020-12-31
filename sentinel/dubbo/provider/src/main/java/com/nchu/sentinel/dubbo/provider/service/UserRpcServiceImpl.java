package com.nchu.sentinel.dubbo.provider.service;

import com.nchu.example.sentinel.dubbo.api.dto.UserDTO;
import com.nchu.example.sentinel.dubbo.api.serivce.UserRpcService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/12/31 16:32
 **/
@Service(version = "${dubbo.provider.UserRpcService.version}")
public class UserRpcServiceImpl implements UserRpcService {
    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("nchu：")
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }
}
