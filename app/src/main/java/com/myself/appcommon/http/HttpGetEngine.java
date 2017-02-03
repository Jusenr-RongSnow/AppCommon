package com.myself.appcommon.http;

import com.myself.appcommon.http.command.BaseHttpRequest;

import org.apache.http.client.methods.HttpGet;

/**
 * 网络请求方法Get
 *
 * @author widebluesky
 */
public class HttpGetEngine extends HttpEngine {

    public HttpGetEngine(BaseHttpRequest request) {
        super(request);
    }

    @Override
    protected void initTag() {
        TAG = HttpGetEngine.class.getSimpleName();
    }

    @Override
    protected void initRequest() {
        requestBase = new HttpGet(baseRequest.getUrl());
    }

    @Override
    protected void setRequestParams() {
    }

}
