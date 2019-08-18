package com.common.district.common.utils;

import java.io.Serializable;
import java.util.List;

public class JsonResponse<T> implements Serializable {
    //常规
    private boolean success;
    private String msg;
    private T t;
    private List<T> lst;
    //用于分页
    private Integer total;
    private List<Object> resultList;
    public JsonResponse setPage(Integer pageCount,List<T> dataList,String msg){
        JsonResponse jsonResponse=new JsonResponse();
        jsonResponse.setSuccess(true);
        jsonResponse.setMsg(msg);
        jsonResponse.setTotal(StringUtils.isBlank(pageCount)?0:pageCount);
        jsonResponse.setResultList(dataList);
        return jsonResponse;
    }
    public static JsonResponse success(String msg){
        JsonResponse jsonResponse=new JsonResponse();
        jsonResponse.setSuccess(true);
        jsonResponse.setMsg(msg);
        return jsonResponse;
    }
    public static JsonResponse error(String msg){
        JsonResponse jsonResponse=new JsonResponse();
        jsonResponse.setSuccess(false);
        jsonResponse.setMsg(msg);
        return jsonResponse;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getLst() {
        return lst;
    }

    public void setLst(List<T> lst) {
        this.lst = lst;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }
}
