package com.nchu.boot.demo.token.service.impl;

import com.nchu.boot.demo.constants.ServiceExceptionEnum;
import com.nchu.boot.demo.exception.ServiceException;
import com.nchu.boot.demo.redis.RedisService;
import com.nchu.boot.demo.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Decription Token服务类
 * @Author yangsj
 * @Date 2020/4/23 11:35
 **/
@Service

public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisService redisService;

    // 存入redis缓存中字符串 key 和 value 前缀
    private static final String  TOKEN_PREFIX = "redis:";

    // 请求头或请求体中参数的key
    private static final String  TOKEN_NAME = "token";


      /**
     * @Description 创建token
     * @Author yangsj
     * @Date 2020/4/24 16:07
     **/
    @Override
    public String createToken() {
        // 使用UUID随机生成一个字符串
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        try {
            // 在随机生成的字符串前拼接前缀
            token.append(TOKEN_PREFIX).append(str);
            // 将token存入redis，并设置token过期时间
            redisService.setEx(token.toString(), token.toString(), 10000L);
            boolean empty = StringUtils.isEmpty(token.toString());
            if (!empty) {
                // 创建成功则返回 token
                return token.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 检验token
     * @Author yangsj
     * @Date 2020/4/24 16:09
     **/
    @Override
    public boolean checkToken(HttpServletRequest request) {
        //从请求头中获取 token
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {// header中不存在token
            // 从请求体中获取 token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {// parameter中也不存在token
                throw new ServiceException(ServiceExceptionEnum.MISS_TOKEN_ERROR);
            }
        }
        // 如果不存在，则抛出异常
        if (!redisService.exists(token)) {
            throw new ServiceException(ServiceExceptionEnum.REPEATED_REQUESTS_ERROR);
        }
        // 如果存在则删除
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new ServiceException(ServiceExceptionEnum.REPEATED_REQUESTS_ERROR);
        }
        return true;
    }
}