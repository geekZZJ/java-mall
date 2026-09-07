package org.example.mall.exception;

public enum MallExceptionEnum {
    NEED_USER_NAME(201, "用户名不能为空"),

    NEED_PASSWORD(202, "密码不能为空"),

    SHORT_PASSWORD(203, "密码长度不能小于8位"),

    SAME_USER_NAME(204, "不允许重名"),

    CREATE_USER_FAIL(205, "创建失败，请重试"),

    SYSTEM_ERROR(500, "系统异常");

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
