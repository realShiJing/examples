package com.nchu.sentinel.quickstart.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Decription 全局异常处理
 * @Author yangsj
 * @Date 2020/12/28 14:46
 **/
@ControllerAdvice(basePackages = "com.nchu.sentinel.quickstart.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BlockException.class)
    public ResponseEntity<?> sentinelBlockHandler(HttpServletRequest request, BlockException blockException) {
        //限流后设置响应信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("address", request.getLocalAddr());
        map.put("message",blockException.getRule());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(map);
    }
}
