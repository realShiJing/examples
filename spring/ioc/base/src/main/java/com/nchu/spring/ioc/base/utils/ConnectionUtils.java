package com.nchu.spring.ioc.base.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Decription 数据库连接工具类
 * @Author yangsj
 * @Date 2020/11/4 10:50 下午
 **/
public class ConnectionUtils {
    // 存储当前线程的连接
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * @Description  获取当前线程的数据库连接
     * @Author yangsj
     * @Date 2020/11/4 10:54 下午
     **/
    public  Connection getCurrentThreadConn() throws SQLException {
        // 从线程局部变量获取当前线程持有的数据库连接
        Connection connection = threadLocal.get();
        // 如果当前线程的数据库连接为空，则获取一个新的数据连接，并设置给当前线程
        if(connection == null){
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        // 最后返回当前线程的数据连接
        return connection;
    }
}
