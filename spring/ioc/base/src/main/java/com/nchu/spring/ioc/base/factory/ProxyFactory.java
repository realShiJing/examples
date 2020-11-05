package com.nchu.spring.ioc.base.factory;

import com.nchu.spring.ioc.base.transaction.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Decription 代理工厂
 * @Author yangsj
 * @Date 2020/11/4 11:26 下午
 **/
public class ProxyFactory {
    // 注入事务管理器
    private TransactionManager transactionManager;

    /**
     *  返回经过事务处理的代理对象
     * @param target 目标对象
     * @return 代理对象
     */
    public Object getJdkProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            Object result = null;
            // 开启事务
            transactionManager.startTransaction();
            //执行目标方法
            try{
                result = method.invoke(target, args);
                // 提交事务
                transactionManager.commitTransaction();
            }catch (Exception e){
                e.printStackTrace();
                //回滚事务
                transactionManager.rollbackTransaction();
                throw e;//抛出异常，待上层处理返回给前台
            }
            return result;
        });
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
