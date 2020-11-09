package com.nchu.spring.ioc.xml.factory;

import com.nchu.spring.ioc.xml.pojo.Account;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Decription 工厂Bean
 *  可以让我们自定义Bean的创建过程(完成复杂Bean的定义)
 * @Author yangsj
 * @Date 2020/11/6 3:48 下午
 **/
public class AccountFactory implements FactoryBean<Account> {
    private String account;
    /**
     * @Description  返回FactoryBean创建的Bean实例，如果isSingleton返回true，则该实例会放到Spring容器 的单例对象缓存池中Map
     * @Author yangsj
     * @Date 2020/11/6 3:50 下午
     **/
    @Override
    public Account getObject() throws Exception {
        String[] accounts = account.split(",");

        Account account = new Account();
        account.setName(accounts[0]);
        account.setCardNo(accounts[1]);
        account.setMoney(Integer.valueOf(accounts[2]));
        return account;
    }
    // 返回FactoryBean创建的Bean类型
    @Override
    public Class<?> getObjectType() {
        return Account.class;
    }
    // 返回作用域是否单例
    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
