package me.jinkun.opennews.network;

import java.util.List;

/**
 * Description: Do one thing at a time, and do well.</br>
 * Autor: Created by jinkun on 2016/3/27.
 */
public class ApiResp<T> {
    boolean flag;
    String code;
    String msg;
    T data;
    List<T> dataList;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
