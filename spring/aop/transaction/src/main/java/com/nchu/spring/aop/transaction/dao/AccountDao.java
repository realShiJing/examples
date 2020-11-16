package com.nchu.spring.aop.transaction.dao;



import com.nchu.spring.aop.transaction.pojo.Account;

import java.sql.SQLException;

public interface AccountDao {

    /**
     * @Description 查询账户
     * @Author yangsj
     * @Date 2020/11/4 4:26 下午
     **/
    Account queryAccountByCardNo(String cardNo) throws SQLException, Exception;

    /**
     * @Description 更新账户余额
     * @Author yangsj
     * @Date 2020/11/4 4:27 下午
     **/

    int updateAccountByCardNo(Account account) throws SQLException, Exception;

}
