package com.nchu.spring.ioc.xmlano.pojo;

/**
 * @Decription 账户
 * @Author yangsj
 * @Date 2020/11/4 4:24 下午
 **/
public class Account {
    // 账号
    private String cardNo;
    // 姓名
    private String name;
    // 账户余额
    private int money;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
