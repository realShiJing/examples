package com.nchu.sentinel.dubbo.consumer.controller;

import com.nchu.example.sentinel.dubbo.api.dto.UserDTO;
import com.nchu.example.sentinel.dubbo.api.serivce.UserRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/12/31 16:48
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "${dubbo.consumer.UserRpcService.version}")
    private UserRpcService userRpcService;

    @GetMapping("/get")
    public UserDTO getUser(@RequestParam("id")  Integer id) {
        return userRpcService.get(id);
    }
}
