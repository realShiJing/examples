package com.nchu.boot.demo.token.controller;

import com.nchu.boot.demo.annotation.AutoIdempotent;
import com.nchu.boot.demo.token.service.TokenService;
import com.nchu.boot.demo.vo.ResultVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Decription 业务请求
 * @Author yangsj
 * @Date 2020/4/23 10:50
 **/
@RestController
public class BusinessController {
    @Resource
    private TokenService tokenService;


    /**
     * @Description 每次需要实现幂等的请求前，都需要随机创建一个token值，并存入redis中
     * @Author yangsj
     * @Date 2020/4/24 16:00
     **/
    @RequestMapping("/get/token")
    public ResultVo  getToken() {
        // 创建随机字符串
        String token = tokenService.createToken();
         // 组装返回格式
        ResultVo resultVo = new ResultVo();
        if (!StringUtils.isEmpty(token)) {
            //如果创建的token存入成功,则返回成功信息
            resultVo.setCode(0000);
            resultVo.setMessage("success");
            // 并返回token信息，下次请求需要带上该token
            resultVo.setData(token);
            return resultVo;
        }
        return resultVo;
    }


    /**
     * @Description 需实现幂等性的业务处理
     * @Author yangsj
     * @Date 2020/4/27 9:44
     **/
    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public ResultVo<String> testIdempotence() {
       return  ResultVo.success("业务处理成功！");
    }
}