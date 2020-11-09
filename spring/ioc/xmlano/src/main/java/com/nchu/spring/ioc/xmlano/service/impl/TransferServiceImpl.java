package com.nchu.spring.ioc.xmlano.service.impl;


import com.nchu.spring.ioc.xmlano.dao.AccountDao;
import com.nchu.spring.ioc.xmlano.pojo.Account;
import com.nchu.spring.ioc.xmlano.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        try{
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(from);
            // 模拟异常，测试转账事务一致性
           /* int a = 10 / 0 ;*/
            accountDao.updateAccountByCardNo(to);
            //conn.commit();//提交事务
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

}
