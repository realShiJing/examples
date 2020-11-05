package com.nchu.spring.ioc.base.dao.impl;

import com.nchu.spring.ioc.base.dao.AccountDao;
import com.nchu.spring.ioc.base.pojo.Account;
import com.nchu.spring.ioc.base.utils.ConnectionUtils;
import com.nchu.spring.ioc.base.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/4 4:28 下午
 **/
public class JdbcAccountDaoImpl implements AccountDao {
    private ConnectionUtils connectionUtils;
    /**
     * @Description 查询账户
     * @Author yangsj
     * @Date 2020/11/4 4:26 下午
     **/
    @Override
    public Account queryAccountByCardNo(String cardNo) throws SQLException {
        //从连接池获取连接
       /* Connection con = DruidUtils.getInstance().getConnection();*/
        // 获取当前线程的数据库连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while(resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();
        //数据库连接在这里不能关闭，当前线程的其他操作还需使用
        //con.close();

        return account;
    }

    /**
     * @Description 更新账户余额
     * @Author yangsj
     * @Date 2020/11/4 4:27 下午
     **/

    @Override
    public int updateAccountByCardNo(Account account) throws SQLException {
        // 从连接池获取连接
        // 改造为：从当前线程当中获取绑定的connection连接
       /* Connection con = DruidUtils.getInstance().getConnection();*/
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,account.getMoney());
        preparedStatement.setString(2,account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        //con.close();
        return i;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }
}
