package com.myself.appcommon.http.model;

import com.myself.appcommon.http.HttpEngine;

import java.util.Map;

/**
 * 网络响应结果Model
 *
 * @author widebluesky
 */
public class HttpResult {

    public static final int RES_OK = 1;

    /**
     * 结果码
     */
    private HttpEngine.HttpCode resultCode;

    /**
     * 结果数据
     */
    private byte[] data;

    /**
     * 结果header\cookie等
     */
    private Map<String, String> headParams = null;

    public HttpEngine.HttpCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(HttpEngine.HttpCode resultCode) {
        this.resultCode = resultCode;
    }


    public byte[] getData() {
        return data;
    }


    public void setData(byte[] data) {
        this.data = data;
    }

    public Map<String, String> getHeadParams() {
        return headParams;
    }

    public void setHeadParams(Map<String, String> headParams) {
        this.headParams = headParams;
    }

}
