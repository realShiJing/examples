package com.nchu.spring.ioc.anno.servlet;


import com.nchu.spring.ioc.anno.factory.ProxyFactory;
import com.nchu.spring.ioc.anno.pojo.Result;
import com.nchu.spring.ioc.anno.service.TransferService;
import com.nchu.spring.ioc.anno.utils.JsonUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Decription Controller层，处理用户转账请求
 * @Author yangsj
 * @Date 2020/11/4 4:11 下午
 **/
@WebServlet(name = "transferServlert" ,urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    private TransferService transferService = null;

    @Override
    public void init() throws ServletException {
        // 获取Web应用全局上下文
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ProxyFactory proxyFactory = (ProxyFactory) webApplicationContext.getBean("proxyFactory");
        transferService = (TransferService) proxyFactory.getJdkProxy(webApplicationContext.getBean("transferService"));

    }

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
