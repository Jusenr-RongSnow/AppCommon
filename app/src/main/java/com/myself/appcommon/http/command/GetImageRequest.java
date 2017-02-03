package com.myself.appcommon.http.command;

import com.myself.appcommon.config.Constants;
import com.myself.appcommon.http.HttpEngine;
import com.myself.appcommon.receiver.NetStatusReceiver;
import com.myself.appcommon.util.LogUtil;

/**
 * 图片下载请求
 *
 * @author widebluesky
 */
public class GetImageRequest extends BaseHttpRequest {
    private Object tag;
    private String filePath;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public HttpEngine.HttpCode prepareRequest() {
        // TODO Auto-generated method stub
        HttpEngine.HttpCode code;

        if (NetStatusReceiver.netStatus == NetStatusReceiver.NETSTATUS_INAVAILABLE) {
            code = HttpEngine.HttpCode.ERROR_NO_CONNECT;
            return code;
        }

        /**
         * 查看是否已取消
         */
        if (isCancelled()) {
            code = HttpEngine.HttpCode.USER_CANCELLED;
            return code;
        }

        /**
         * 检查URL是否正确，已经是否有用户信息
         */
        code = checkUrlParams();

        if (code == HttpEngine.HttpCode.STATUS_OK) {
            /**
             * 加入用户身份信息
             */
            if (isNeedAuth()) {
                addUserVerifyInfo();
            }
            /**
             * 已经在图片请求初期就在generateImageID()中调用，不需要再去执行。
             */
            //	makeUrlWithSystemInfo();
        }
        LogUtil.d(Constants.APP_TAG, "[System]: URL = " + getUrl());
        return code;
    }
}
