package com.nchu.spring.aop.transaction.service.impl;

import com.nchu.spring.aop.transaction.service.TransferService;
import com.nchu.spring.aop.transaction.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/13 2:17 下午
 **/
@Service("userService")
public class UserSerivceImpl implements UserSerivce {
    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUser(String name) {
        // 插入user 记录
        jdbcTemplate.update("INSERT INTO `user` (name) VALUES(?)", name);
    }

}
