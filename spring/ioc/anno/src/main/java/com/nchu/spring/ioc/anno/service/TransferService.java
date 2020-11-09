package com.nchu.spring.ioc.anno.service;

import java.sql.SQLException;

/**
 * @Description   转账服务
 * @Author yangsj
 * @Date 2020/11/4 4:19 下午
 **/
public interface TransferService {
    /**
     *  转账
     * @param fromCardNo 转出账户
     * @param toCardNo  转入账户
     * @param money 转账金额
     * @throws SQLException
     */
    void transfer(String fromCardNo, String toCardNo, int money) throws  Exception;
}
