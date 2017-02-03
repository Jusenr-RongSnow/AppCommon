package com.myself.appcommon.http;

import com.myself.appcommon.http.command.BaseHttpRequest;

import org.apache.http.client.methods.HttpPut;

import java.io.UnsupportedEncodingException;

/**
 * 网络请求方法Put
 *
 * @author widebluesky
 */
public class HttpPutEngine extends HttpEngine {

    public HttpPutEngine(BaseHttpRequest request) {
        super(request);
    }

    @Override
    protected void initTag() {
        TAG = HttpPutEngine.class.getSimpleName();
    }

    @Override
    protected void initRequest() {
        requestBase = new HttpPut(baseRequest.getUrl());
    }

    @Override
    protected void setRequestParams() throws UnsupportedEncodingException {
    }

}
