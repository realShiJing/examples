package com.nchu.spring.ioc.base.transaction;

import com.nchu.spring.ioc.base.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Decription 事务管理 （切面），负责事务的开启、提交和回滚
 * 以动态代理的方式执行目标方法
 * @Author yangsj
 * @Date 2020/11/4 11:14 下午
 **/
public class TransactionManager {
    private ConnectionUtils connectionUtils;

    /**
     * @Description 开启事务
     * @Author yangsj
     * @Date 2020/11/4 11:19 下午
     **/
    public void startTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }
    /**
     * @Description  提交事务
     * @Author yangsj
     * @Date 2020/11/4 11:21 下午
     **/
    public void commitTransaction() throws SQLException{
        connectionUtils.getCurrentThreadConn().commit();
    }
    /**
     * @Description   回滚事务
     * @Author yangsj
     * @Date 2020/11/4 11:21 下午
     **/
    public void rollbackTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }
}
