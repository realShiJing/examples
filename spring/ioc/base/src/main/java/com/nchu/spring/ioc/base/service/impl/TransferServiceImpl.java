package com.nchu.spring.ioc.base.service.impl;

import com.nchu.spring.ioc.base.dao.AccountDao;
import com.nchu.spring.ioc.base.dao.impl.JdbcAccountDaoImpl;
import com.nchu.spring.ioc.base.pojo.Account;
import com.nchu.spring.ioc.base.service.TransferService;
import com.nchu.spring.ioc.base.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/4 4:21 下午
 **/
public class TransferServiceImpl implements TransferService {
    // new 对象 （强耦合）
    /*  private AccountDao accountDao = new JdbcAccountDaoImpl();*/
    // 只声明依赖的接口信息
    private AccountDao accountDao ;
    // 事务操作侵入到业务代码，耦合性太高，需要将事务操作抽取出来
    /*private ConnectionUtils connectionUtils;*/

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        //开启事务
       /* Connection conn = connectionUtils.getCurrentThreadConn();
        conn.setAutoCommit(false);*/
        try{
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(from);
            // 模拟异常，测试转账事务一致性
            int a = 10 / 0 ;
            accountDao.updateAccountByCardNo(to);
            //conn.commit();//提交事务
        }catch (Exception e){
            e.printStackTrace();
            throw e;
            //conn.rollback();//回滚事务
        }

    }

    // 另外定义注入依赖方法，在BeanFactory中解析xml并注入依赖
    public void setAccountDao(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    /*private void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }*/
}
