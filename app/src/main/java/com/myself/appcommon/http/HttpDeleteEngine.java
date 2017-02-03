package com.myself.appcommon.http;

import com.myself.appcommon.http.command.BaseHttpRequest;

import org.apache.http.client.methods.HttpDelete;

/**
 * 网络请求方法Delete
 *
 * @author widebluesky
 */
public class HttpDeleteEngine extends HttpEngine {

    public HttpDeleteEngine(BaseHttpRequest request) {
        super(request);
    }


    @Override
    protected void initTag() {
        TAG = HttpDeleteEngine.class.getSimpleName();
    }

    @Override
    protected void initRequest() {
        requestBase = new HttpDelete(baseRequest.getUrl());
    }

    @Override
    protected void setRequestParams() {
    }

}
