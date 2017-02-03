package com.myself.appcommon.http.command;

import com.google.gson.Gson;
import com.myself.appcommon.api.HttpTag;
import com.myself.appcommon.config.Constants;
import com.myself.appcommon.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 网络请求数据分发类
 *
 * @author widebluesky
 */
public class HttpTagDispatch {

    private static final String TAG = HttpTagDispatch.class.getSimpleName();

    public static Object dispatch(HttpDataRequest request, String json) throws Exception {
        Object result = json;
        HttpTag tag = request.getTag();
        switch (tag.getTagType()) {
            case Constants.TAG_TYPE_STRING:
                result = json;
                break;
            case Constants.TAG_TYPE_GSON:
                result = new Gson().fromJson(json, tag.getClass());
                break;
            case Constants.TAG_TYPE_JSON_OBJECT:
                result = new JSONObject(json);
                break;
            case Constants.TAG_TYPE_JSON_ARRAY:
                result = new JSONArray(json);
            default:
                result = json;
                break;
        }
        LogUtil.i(TAG, "ResponseData [tag=" + request.getTag() + ", json=" + json + "]");
        return result;
    }
}
