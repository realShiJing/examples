package com.nchu.spring.ioc.base.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @Decription 数据库连接
 * @Author yangsj
 * @Date 2020/11/4 4:31 下午
 **/
public class DruidUtils {
    private DruidUtils() {
    }

    private static DruidDataSource dataSource = new DruidDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
    }

    public static DruidDataSource getInstance(){
        return dataSource;
    }

}
