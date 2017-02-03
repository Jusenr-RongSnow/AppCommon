package com.myself.appcommon.util;


import android.util.Log;

import com.myself.appcommon.config.Constants;

/**
 * 日志工具类
 *
 * @author widebluesky
 */
public class LogUtil {

    private static boolean isDebugMode = Constants.IS_TEST;

    private LogUtil() {

    }

    public static void d(String tag, String msg) {
        if (isDebugMode) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebugMode) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebugMode) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebugMode) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebugMode) {
            Log.v(tag, msg);
        }
    }
}
