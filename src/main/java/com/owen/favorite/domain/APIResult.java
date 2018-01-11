package com.owen.favorite.domain;

import com.owen.favorite.constant.ApiConstant;

public class APIResult {

    private int code;
    private String message;
    private Object data;

    public static APIResult createOk(Object data) {
        return createWithCodeAndData(ApiConstant.Code.OK, null, data);
    }

    public static APIResult createOKMessage(String message) {
        APIResult result = new APIResult();
        result.setCode(ApiConstant.Code.OK);
        result.setMessage(message);
        return result;
    }

    public static APIResult createNg(String message) {
        return createWithCodeAndData(ApiConstant.Code.NG, message, null);
    }

    private static APIResult createWithCodeAndData(int code, String message, Object data) {
        APIResult result = new APIResult();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}