package com.nchu.cor;

import java.util.Random;

/**
 * @ClassName: Director 主管处理者
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 13:52
 **/
public class Director extends Handler {

    public Director(String name) {
        super(name);
    }

    @Override
    public boolean process(LeaveRequest leaveRequest) {
        boolean result = (new Random().nextInt(10)) > 3; // 随机数大于3则为批准，否则不批准
        String log = "主管<%s> 审批 <%s> 的请假申请，请假天数： <%d> ，审批结果：<%s> ";
        System.out.println(String.format(log, this.name, leaveRequest.getName(), leaveRequest.getNumOfDays(), result == true ? "批准" : "不批准"));

        if (result == false) {  // 不批准
            return false;
        } else if (leaveRequest.getNumOfDays() < 3) { // 批准且天数小于3，返回true
            return true;
        }
        return nextHandler.process(leaveRequest);   // 批准且天数大于等于3，提交给下一个处理者处理
    }
}
