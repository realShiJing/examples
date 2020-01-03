package com.nchu.allocator;

import org.junit.Test;

/**
 * @Decription 多线程并发转账测试
 * @Author yangsj
 * @Date 2020/1/3 15:22
 **/
public class App {
    @Test
    public void test(){
        try {
           /* Object lock = new Object();*/
            for (int i = 0; i < 100000; i++) {
                Account accountA = new Account(200,1);
                Account accountB = new Account(200,2);
                //Account accountC= new Account(200);
                Thread threadA = new Thread(()->{
                    accountA.transfer(accountB,100);
                });
                Thread threadB = new Thread(()->{
                    accountB.transfer(accountA,100);
                });

                threadA.start();
                threadB.start();

                threadA.join();
                threadB.join();

                if(accountA.getBalance() != 200 || accountB.getBalance() != 200){
                    System.out.println("AccountA balance is:"+ accountA.getBalance());
                    System.out.println("AccountB balance is:"+ accountB.getBalance());
                    //System.out.println("AccountC balance is:"+ accountC.getBalance());
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
