package com.seezoon.user.infrastructure.error;

import com.seezoon.user.infrastructure.exception.ErrorDefinition;

public enum ErrorCode implements ErrorDefinition {
    /**
     * 建议公共错误定义在1000-2000 的范围
     */
    UNKNOWN(1000, "system error"),

    PARAM_INVALID(1001, "param invalid, %s"),

    SQL_ERROR(1002, "sql error", true),
    ASSERTION_ERROR(1003, "assertion error"),
    RPC_ERROR(1004, "远程调用错误"),

    WX_ERROR(3000, "调用微信出错，请联系管理员"),
    USER_PWD_ERROR(3001, "账号密码错误，错误6次后将锁定"),
    FILE_UPLOAD_FAILED(3002, "文件上传错误，请重试"),
    FILE_NOT_EXISTS(3003, "文件不存在"),
    FILE_SIZE_INVALID(3004, "文件大小不合法");


    public static final int ERROR_TYPE_BUSINESS = 0;
    public static final int ERROR_TYPE_SYSTEM = 1;

    private int code;
    private String msg;

    private int type;


    ErrorCode(int code, String msg) {
        this(code, msg, false);
    }

    ErrorCode(int code, String msg, boolean sysError) {
        this.code = code;
        this.msg = msg;
        this.type = sysError ? ERROR_TYPE_SYSTEM : ERROR_TYPE_BUSINESS;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }

    @Override
    public int type() {
        return type;
    }

    public boolean IsSystemError() {
        return type == ERROR_TYPE_SYSTEM;
    }
}
