package com.nchu.cor;

/**
 * @ClassName: Handler
 * @Description: 抽象处理者 每个处理者中都包含下一个处理者
 * @Author: yangsj
 * @Date:Create： 2021/1/18 13:51
 **/
public abstract class Handler {
    protected String name; // 处理者姓名
    protected Handler nextHandler;  // 下一个处理者



    public void setName(String name) {
        this.name = name;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler(String name) {
        this.name = name;
    }

    public abstract boolean process(LeaveRequest leaveRequest); // 处理请假
}
