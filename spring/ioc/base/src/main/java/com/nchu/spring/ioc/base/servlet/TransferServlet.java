package com.nchu.spring.ioc.base.servlet;

import com.nchu.spring.ioc.base.factory.BeanFactory;
import com.nchu.spring.ioc.base.factory.ProxyFactory;
import com.nchu.spring.ioc.base.pojo.Result;
import com.nchu.spring.ioc.base.service.TransferService;
import com.nchu.spring.ioc.base.service.impl.TransferServiceImpl;
import com.nchu.spring.ioc.base.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Decription Controller层，处理用户转账请求
 * @Author yangsj
 * @Date 2020/11/4 4:11 下午
 **/
@WebServlet(name = "transferServlert" ,urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {
    // new 对象 （强耦合）
    /*private TransferService transferService = new TransferServiceImpl();*/

    // Bean工厂从xml文件中获取实例（弱耦合）
   /* private TransferService transferService = (TransferService) BeanFactory.getBean("transferService");*/

    // 从自定义IOC容器中获取代理工厂
    private ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");

    // 从代理工厂中获取经过事务处理的服务
    private TransferService transferService = (TransferService) proxyFactory.getJdkProxy(BeanFactory.getBean("transferService"));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 转出账户
        String fromCardNo = req.getParameter("fromCardNo");
        // 转入账户
        String toCardNo = req.getParameter("toCardNo");
        // 转账金额
        String moneyStr = req.getParameter("money");

        int money = Integer.parseInt(moneyStr);
        Result result = new Result();
        try {
            //更新成功
            result.setMessage("success");
            result.setStatus("200");
            transferService.transfer(fromCardNo,toCardNo,money);

        } catch (Exception e) {
            // 更新失败
            e.printStackTrace();
            result.setMessage(e.toString());
            result.setStatus("500");
        }
        // 以json串的形式返回
        resp.setContentType("application/json;charset=utf-8");
        // 将结果转换为json串
        resp.getWriter().print(JsonUtils.object2Json(result));

    }
}
