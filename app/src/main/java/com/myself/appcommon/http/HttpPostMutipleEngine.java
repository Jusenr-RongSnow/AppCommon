package com.myself.appcommon.http;

import com.myself.appcommon.http.command.BaseHttpRequest;
import com.myself.appcommon.http.command.HttpPostRequest;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;

/**
 * 网络请求方法Post
 *
 * @author widebluesky
 */
public class HttpPostMutipleEngine extends HttpEngine {

    public HttpPostMutipleEngine(BaseHttpRequest request) {
        super(request);
    }

    @Override
    protected void initTag() {
        TAG = HttpPostMutipleEngine.class.getSimpleName();
    }

    @Override
    protected void initRequest() {
        requestBase = new HttpPost(baseRequest.getUrl());
    }

    @Override
    protected void setRequestParams() {
        HttpPostRequest postRequest = (HttpPostRequest) baseRequest;
        AbstractHttpEntity entity = new ByteArrayEntity(postRequest.getBytes());
        entity.setContentType("multipart/form-data; boundary=" + HttpPostRequest.BOUNDARY);
        ((HttpPost) requestBase).setEntity(entity);
    }

}
