package com.nchu.allocator;

/**
 * @Decription 银行账户
 * @Author yangsj
 * @Date 2020/1/3 15:18
 **/
public class Account {
    // 账户余额
    private int balance;

    /*private Object lock;*/


    Account(int balance/*,Object lock*/){
        this.balance = balance;
        /*this.lock = lock;*/
    }
    /**
     * 转账
     * @param target 目标账户
     * @param amt 转出金额
     */
    public synchronized void transfer(Account target, int amt) {
       /* synchronized (lock){*/
         synchronized (Account.class){
            if(this.balance < amt){
                return;
            }
            // 从当前账户中划扣金额
            this.balance -= amt;
            // 向目标账户中转入金额
            target.balance += amt;
        }
    }

    public int getBalance() {
        return balance;
    }
}
