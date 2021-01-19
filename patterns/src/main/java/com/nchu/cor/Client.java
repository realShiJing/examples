package com.nchu.cor;

/**
 * @ClassName: Client
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 13:55
 **/
public class Client {
    public static void main(String[] args) {
        Handler zhangsan = new Director("张三");
        Handler lisi = new Manager("李四");
        Handler wangwu = new TopManager("王五");

        // 创建责任链
        zhangsan.setNextHandler(lisi);
        lisi.setNextHandler(wangwu);
        // 发起请假申请
        boolean result1 = zhangsan.process(new LeaveRequest("yangsj", 1));
        System.out.println("最终结果：" + result1 + "\n");

        boolean result2 = zhangsan.process(new LeaveRequest("yangsj", 4));
        System.out.println("最终结果：" + result2 + "\n");

        boolean result3 = zhangsan.process(new LeaveRequest("yangsj", 8));
        System.out.println("最终结果：" + result3 + "\n");
    }
}
