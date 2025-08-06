package com.noz.identity.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error"),
    USER_EXISTED(1002, "User existed"),
    INVALID_KEY(1001,"Invalid message key"),
    USERNAME_INVALID(1003,"Username must be at least 3 character"),
    INVALID_PASSWORD(1004,"Password must be at least 9 character"),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006,"Unauthenticated")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
