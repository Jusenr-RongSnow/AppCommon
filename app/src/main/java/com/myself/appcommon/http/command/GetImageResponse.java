package com.myself.appcommon.http.command;

import android.graphics.Bitmap;

import com.myself.appcommon.http.model.ImageType;

/**
 * 图片下载响应接口
 *
 * @author widebluesky
 */
public interface GetImageResponse {
    void onImageRecvOK(ImageType imageType, Object tag, Bitmap bm, String path);

    void onImageRecvError(ImageType imageType, Object tag, int retCode);
}
