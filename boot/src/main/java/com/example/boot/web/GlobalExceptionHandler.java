package com.example.boot.web;

import com.example.boot.constants.ServiceExceptionEnum;
import com.example.boot.exception.ServiceException;
import com.example.boot.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description 统一异常处理
 * @Author yangsj
 * @Date 2020/4/24 16:17
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public ResultVo serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        // 包装 ResultVo 结果
        return ResultVo.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     *
     * SpringMVC 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResultVo missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        logger.debug("[missingServletRequestParameterExceptionHandler]", ex);
        // 包装 ResultVo 结果
        return ResultVo.error(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),
                ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo exceptionHandler(HttpServletRequest req, Exception e) {
        // 记录异常日志
        logger.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return ResultVo.error(ServiceExceptionEnum.SYS_ERROR.getCode(),
                ServiceExceptionEnum.SYS_ERROR.getMessage());
    }

}
