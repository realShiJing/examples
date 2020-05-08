package com.nchu.cloud.vo;

import java.io.Serializable;

/**
 * 通用返回结果
 *
 * @param <T> 结果泛型
 */
public class ResultVo<T> implements Serializable {

    public static Integer CODE_SUCCESS = 0;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;



    /**
     * @Description 错误信息返回格式
     * @Author yangsj
     * @Date 2020/4/24 16:19
     **/
    public static <T> ResultVo<T> error(Integer code, String message) {
        ResultVo<T> result = new ResultVo<>();
        result.code = code;
        result.message = message;
        return result;
    }


    /**
     * @Description 正确信息返回格式
     * @Author yangsj
     * @Date 2020/4/24 16:20
     **/
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = "success";
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
