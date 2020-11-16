package com.nchu.spring.aop.transaction.service.impl;


import com.nchu.spring.aop.transaction.dao.AccountDao;
import com.nchu.spring.aop.transaction.pojo.Account;
import com.nchu.spring.aop.transaction.service.TransferService;
import com.nchu.spring.aop.transaction.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/4 4:21 下午
 **/
@Service("transferService")
public class TransferServiceImpl implements TransferService {
    // 只声明依赖的接口信息
    @Autowired
    private AccountDao accountDao ;

    @Autowired
    UserSerivce userSerivce;

    @Transactional
    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        try{
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(from);
            // 模拟异常，测试转账事务一致性
            accountDao.updateAccountByCardNo(to);
            userSerivce.createUser(from.getName());
            int a = 10 / 0 ;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
