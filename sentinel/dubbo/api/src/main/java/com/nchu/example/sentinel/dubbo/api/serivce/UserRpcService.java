package com.nchu.example.sentinel.dubbo.api.serivce;


import com.nchu.example.sentinel.dubbo.api.dto.UserDTO;

/**
 * 用户服务 RPC Service 接口
 */
public interface UserRpcService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(Integer id);

}
