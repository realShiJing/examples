package com.nchu.spring.aop.transaction.dao.impl;

import com.nchu.spring.aop.transaction.dao.AccountDao;
import com.nchu.spring.aop.transaction.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/4 4:28 下午
 **/
@Repository("accountDao")
public class JdbcAccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Description  查询账户
     * @Author yangsj
     * @Date 2020/11/11 5:19 下午
     **/
    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        String sql = "select * from account where cardNo=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setName(resultSet.getString("name"));
                account.setCardNo(resultSet.getString("cardNo"));
                account.setMoney(resultSet.getInt("money"));
                return account;
            }
        }, cardNo);
    }

    /**
     * @Description  更新账户余额
     * @Author yangsj
     * @Date 2020/11/11 5:19 下午
     **/
    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        String sql = "update account set money=? where cardNo=?";
        return jdbcTemplate.update(sql,account.getMoney(),account.getCardNo());
    }
}
