package com.myself.appcommon.http.command;

import com.myself.appcommon.api.HttpTag;
import com.myself.appcommon.config.Constants;
import com.myself.appcommon.http.HttpEngine;
import com.myself.appcommon.receiver.NetStatusReceiver;
import com.myself.appcommon.util.LogUtil;

/**
 * 通用网络请求
 *
 * @author widebluesky
 */
public class HttpDataRequest extends BaseHttpRequest {
    private HttpTag tag;

    private Object extraInfo;

    public HttpTag getTag() {
        return tag;
    }

    public void setTag(HttpTag tag) {
        this.tag = tag;
        this.setUrl(tag.getHttpUrl());
    }

    public Object getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Object extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        HttpDataRequest other = (HttpDataRequest) obj;
        if (tag != other.tag)
            return false;
        return true;
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
             * 参数检查成功
             */
            makeUrlWithSystemInfo();
        }
        LogUtil.d(Constants.APP_TAG, "[System]: URL = " + getUrl());
        return code;
    }

}
