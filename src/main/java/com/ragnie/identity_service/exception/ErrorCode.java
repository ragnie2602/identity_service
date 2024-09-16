package com.ragnie.identity_service.exception;

public enum ErrorCode {
    USER_EXITED(400, "Username existed"),
    METHOD_NOT_ALLOWED(405, "Method not allowed"),
    INVALID_KEY(901, "Invalid message key"),
    INVALID_PASSWORD(409, "Password must be at least 8 characters"),
    INVALID_USERNAME(409, "Username must be at least 4 characters"),
    UNCATEGORIED_ERROR(999, "Unknown error");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}