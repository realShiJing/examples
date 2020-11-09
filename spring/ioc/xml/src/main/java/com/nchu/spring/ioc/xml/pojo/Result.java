package com.nchu.spring.ioc.xml.pojo;

/**
 * @Decription 响应信息
 * @Author yangsj
 * @Date 2020/11/4 4:16 下午
 **/
public class Result {
    //返回数据
    private String message;
    //状态码
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
