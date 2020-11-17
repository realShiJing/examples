package com.nchu.boot.demo.constants;

/**
 * 业务异常枚举
 */
public enum ServiceExceptionEnum {

    // ========== 系统级别 ==========
    SYS_ERROR(1000, "error"),
    MISS_TOKEN_ERROR(1000, "请求信息缺失Tosken!"),
    REPEATED_REQUESTS_ERROR(1001, "重复请求!"),
    MISSING_REQUEST_PARAM_ERROR(1002, "参数缺失"),;

    // 错误码
    private final int code;
    // 错误提示
    private final String message;

    ServiceExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
