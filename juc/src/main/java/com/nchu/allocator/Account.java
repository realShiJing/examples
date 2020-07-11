package com.nchu.allocator;

/**
 * @Decription 银行账户
 * @Author yangsj
 * @Date 2020/1/3 15:18
 **/
public class Account {
    // 账户余额
    private int balance;

    // 锁管理者
    private  Allocator allocator = Allocator.getInstance();
   /* private int id;*/
    /*private Object lock;*/


    Account(int balance/*,Object lock*//*,int id*/){
        this.balance = balance;
        /*this.lock = lock;*/
        /*this.id = id;*/
    }
    /**
     * 转账
     * @param target 目标账户
     * @param amt 转出金额
     */
    public void transfer(Account target, int amt) {
        /*Account left = this;
        Account right = target;
        if(this.id > target.id){
            left = target;
            right = this;
        }*/
       /* synchronized (lock){*/
        /* synchronized (Account.class){*/
        // 循环申请锁，直至申请成功
        // 一次性申请所有资源
        allocator.apply(this,target);
        try {
            synchronized (this) {
                synchronized (target){
                    if(this.balance < amt){
                        return;
                    }
                    // 从当前账户中划扣金额
                    this.balance -= amt;
                    // 向目标账户中转入金额
                    target.balance += amt;
                }
            }
        }finally {
            // 释放锁资源
            allocator.free(this,target);
        }


        /*}*/
    }

    public int getBalance() {
        return balance;
    }
}
