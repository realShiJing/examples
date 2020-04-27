package com.example.boot.web;

import com.example.boot.vo.ResultVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @Description 统一返回结果处理
 * 只拦截我们的 Controller 所在包，避免其它类似 swagger 提供的 API 被切面拦截
 * @Author yangsj
 * @Date 2020/4/24 16:18
 **/
@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 如果已经是 ResultVo 类型，则直接返回
        if (body instanceof ResultVo) {
            return body;
        }
        // 如果不是，则包装成 ResultVo 类型
        return ResultVo.success(body);
    }

}
