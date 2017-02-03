package com.myself.appcommon.http.command;

import com.myself.appcommon.api.HttpTag;
import com.myself.appcommon.http.HttpEngine;


/**
 * 通用网络请求响应接口
 *
 * @author widebluesky
 */
public interface HttpDataResponse {

    /**
     * 网络请求完成
     *
     * @param tag
     * @param extraInfo
     * @param result
     */
    void onHttpRecvOK(HttpTag tag, Object extraInfo, Object result);

    /**
     * 网络请求异常
     *
     * @param tag
     * @param retCode
     * @param msg
     */
    void onHttpRecvError(HttpTag tag, HttpEngine.HttpCode retCode, String msg);

    /**
     * 网络请求退出
     *
     * @param tag
     */
    void onHttpRecvCancelled(HttpTag tag);


}
