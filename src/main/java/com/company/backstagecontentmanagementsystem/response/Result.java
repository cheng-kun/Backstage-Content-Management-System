package com.company.backstagecontentmanagementsystem.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Result<T> {

    public static final int STATUS_YES = 1;
    public static final int STATUS_NO = 0;
    private int status;
    private T data;
    private ErrorCode errorCode;

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public Result(int status, ErrorCode errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public static Result createYesResult() {
        return new Result(STATUS_YES);
    }

    public static <T> Result createYesResult(T data) {
        return new Result<>(STATUS_YES, data);
    }

    public static Result createNoResult(ErrorCode errorCode) {
        return new Result(STATUS_NO, errorCode);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", data=" + data +
                ", errorCode=" + errorCode +
                '}';
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum ErrorCode {
        UNKNOWN_ERROR(100, "UNKNOWN_ERROR"),
        LOGIN_FAILED(101, "LOGIN_FAILED"),
        REPEATED_USERNAME(102, "REPEATED_USERNAME"),
        LOGOUT_FAILED(103, "LOGOUT_FAILED"),
        NOT_LOGIN(104, "NOT_LOGIN"),
        REGISTER_FAILED(105, "REGISTER_FAILED"),
        REPEATED_PHONE(106, "REPEATED_PHONE"),
        USER_NOT_EXIST(107, "USER_NOT_EXIST"),
        CREATE_STORE_FAILED(200, "CREATE_STORE_FAILED"),
        UPDATE_STORE_FAILED(201,"UPDATE_STORE_FAILED"),
        QUERY_STORE_FAILED(202,"QUERY_STORE_FAILED"),
        CREATE_CATEGORY_FAILED(300, "CREATE_CATEGORY_FAILED"),
        UPDATE_CATEGORY_FAILED(301, "UPDATE_CATEGORY_FAILED"),
        DELETE_CATEGORY_FAILED(302, "DELETE_CATEGORY_FAILED"),
        QUERY_CATEGORY_FAILED(304, "QUERY_CATEGORY_FAILED"),
        CREATE_GOODS_FAILED(400, "CREATE_GOODS_FAILED"),
        UPDATE_GOODS_FAILED(401, "UPDATE_GOODS_FAILED"),
        DELETE_GOODS_FAILED(402, "DELETE_GOODS_FAILED"),
        QUERY_GOODS_FAILED(403, "QUERY_GOODS_FAILED");

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
}