package com.common.district.common.SysUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class RespData<T> implements Serializable {

    private static final long serialVersionUID = 8887608086912964404L;

    private Boolean result;
    // 标识码
    private int errorCode;
    private String errorInfo;
    // 错误信息
    private String errorMessage;
    private JSONObject verifyReason;
    // 数据
    private T data;
    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;

    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 500;

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RespData() {
    }

    public RespData(boolean result) {
        this.result = result;
    }

    public RespData(boolean result, T data) {
        this.result = result;
        this.data = data;
    }

    public RespData(boolean result, T data, JSONObject verifyReason) {
        this.result = result;
        this.data = data;
        this.verifyReason = verifyReason;
    }

    public RespData(Boolean result, int errorCode, String errorMessage, JSONObject verifyReason, T data) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.verifyReason = verifyReason;
        this.data = data;
    }

    private RespData(boolean result, String errorInfo, String errorMessage, T data) {
        this.result = result;
        this.errorInfo = errorInfo;
        this.errorMessage = errorMessage;
        this.data = data;
    }


    /**
     * @param result
     * @param data
     * @param errorMessage
     */
    public RespData(boolean result, T data, String errorMessage) {
        this.result = Boolean.valueOf(result);
        this.data = data;
        this.errorMessage = errorMessage;
    }


    public Boolean getResult() {
        return this.result;
    }
    public Boolean isResult() {
        return this.result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public JSONObject getVerifyReason() {
        return this.verifyReason;
    }

    public void setVerifyReason(JSONObject verifyReason) {
        this.verifyReason = verifyReason;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static RespData success() {
        return new RespData(SUCCESS);
    }

    public static <T> RespData<T> success(T data) {
        return new RespData(SUCCESS, data);
    }

    public static RespData fail(JSONObject verifyReason) {
        return new RespData(FAIL, verifyReason);
    }
    public static RespData fail(String errorCode, String errorMessage){
        return new RespData(FAIL,errorCode, errorMessage,null);
    }
    public static RespData fail(String errorMessage) {
        return new RespData(FAIL, FAIL_CODE, errorMessage, (JSONObject)null, (Object)null);
    }
    public static <T> RespData<T> fail(String errorInfo, String errorMessage, T data){
        return new RespData(FAIL,errorInfo, errorMessage,data);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
