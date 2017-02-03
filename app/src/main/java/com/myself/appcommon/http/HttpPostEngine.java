package com.myself.appcommon.http;

import com.myself.appcommon.http.command.BaseHttpRequest;
import com.myself.appcommon.http.command.HttpPostRequest;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * 网络请求方法Post
 *
 * @author widebluesky
 */
public class HttpPostEngine extends HttpEngine {

    public HttpPostEngine(BaseHttpRequest request) {
        super(request);
    }

    @Override
    protected void initTag() {
        TAG = HttpPostEngine.class.getSimpleName();
    }

    @Override
    protected void initRequest() {
        requestBase = new HttpPost(baseRequest.getUrl());
    }

    @Override
    protected void setRequestParams() throws UnsupportedEncodingException {
        HttpPostRequest postRequest = (HttpPostRequest) baseRequest;
        String entityString = postRequest.getString();
        AbstractHttpEntity entity = new StringEntity(entityString);
        entity.setContentType("application/x-www-form-urlencoded");
        ((HttpPost) requestBase).setEntity(entity);
    }
}
