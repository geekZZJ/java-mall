package org.example.mall.exception;

public enum MallExceptionEnum {
    NEED_USER_NAME(201, "用户名不能为空");

    NEED_PASSWORD(202,"密码不能为空");

    Integer code;
    String message;

    MallExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
