package com.myself.appcommon.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myself.appcommon.R;
import com.myself.appcommon.api.HttpTag;
import com.myself.appcommon.base.BaseActivity;
import com.myself.appcommon.config.Constants;
import com.myself.appcommon.http.HttpEngine;
import com.myself.appcommon.http.command.HttpDataResponse;
import com.myself.appcommon.http.command.HttpPostRequest;
import com.myself.appcommon.http.task.TaskManager;
import com.myself.appcommon.manager.ThemeSettingsHelper;
import com.myself.appcommon.util.DialogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity extends BaseActivity implements HttpDataResponse, ThemeSettingsHelper.ThemeCallback {
    public static final String TAG = "MainActivity";

    // 文章详情页面是否显示评论以及评论和赞数量
    // baidu://pageSetting/{'isComment':'1','commentNumber':'xxx','zanNumber':'xxx'}
    public static String PAGE_SETTING = "pageSetting";

    private Button mBtn;

    private ThemeSettingsHelper mThemeSettingsHelper;
    private WebView mWebview;
    private String titleFromPage;
    private String descriptionFromPage;
    private String sharePicFromPage;

    @Override
    public boolean needTranslucent() {
        return true;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initStaticData() {

    }

    @Override
    public void initData() {
        mWebview.loadUrl(Constants.URL_TEST);
    }

    @Override
    public void initView() {
        mThemeSettingsHelper = ThemeSettingsHelper.getThemeSettingsHelper(this);
        mThemeSettingsHelper.changeTheme(this, ThemeSettingsHelper.THEME_NIGHT);
        setStatusBarAlpha(0);
        mBtn = (Button) findViewById(R.id.test_send_request);
        mWebview = (WebView) findViewById(R.id.test_wv);
        setWebSettings();
    }

    @Override
    public void initListener() {
        mThemeSettingsHelper.registerThemeCallback(this);
        mBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToActivity(NextActivity.class, false);
                sendRequest();
            }
        });
    }

    @Override
    public void onHttpRecvOK(HttpTag tag, Object extraInfo, Object result) {
        DialogUtil.showToast(this, (String) result, Toast.LENGTH_LONG);
//        Log.e(TAG, "onHttpRecvOK: " + result);
    }

    @Override
    public void onHttpRecvError(HttpTag tag, HttpEngine.HttpCode retCode, String msg) {
        DialogUtil.showToast(this, "onHttpRecvError retCode:" + retCode + " msg:" + msg, Toast.LENGTH_LONG);
    }

    @Override
    public void onHttpRecvCancelled(HttpTag tag) {
        DialogUtil.showToast(this, "onHttpRecvCancelled", Toast.LENGTH_LONG);
    }

    private void sendRequest() {
        HttpPostRequest request = new HttpPostRequest();
        request.setTag(HttpTag.TEST);
        request.setSort(Constants.REQUEST_METHOD_POST); // application/x-www-form-urlencoded
        //request.setSort(Constants.REQUEST_METHOD_POST_MULTIPLE); // multipart/form-data
        request.setGzip(true);
        request.setRetry(false);
        request.setNeedAuth(false);
        TaskManager.startHttpDataRequset(request, this);

    }

    @Override
    public void applyTheme() {
        if (mThemeSettingsHelper.isDefaultTheme()) {

        }

    }

    private void setWebSettings() {
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);//开启对JavaScript的支持
        settings.setDefaultTextEncodingName("UTF-8");//设置字符编码

        settings.setSupportZoom(true);
        // 开启alert
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 开启按钮按下显示
        settings.setLightTouchEnabled(true);
//        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        appendUserAgent("version", AppUtils.getNoPrefixVersionName(this));
        mWebview.requestFocus();
        mWebview.setWebChromeClient(chromeClient);
        mWebview.setWebViewClient(webViewClient);
    }

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//            pb_webview.setProgress(newProgress + 30);
//            if (newProgress >= 100)
//                pb_webview.setVisibility(View.GONE);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            showAlert(view, url, message, result);
            return true;
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void getVisitedHistory(ValueCallback<String[]> callback) {
            super.getVisitedHistory(callback);
        }
    };

    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            String protocolHeader = getProtocolHeader(url);
            switch (protocolHeader) {
                case ProtocolHeader.PROTOCOL_HEADER_HTTP:
                    view.loadUrl(url);
                    break;
                case ProtocolHeader.PROTOCOL_HEADER_HTTPS:
                    view.loadUrl(url);
                    break;
                case ProtocolHeader.PROTOCOL_HEADER_BAIDU:
                    // 网页加载完js会通过此方法把 article_title, description, share_pic 传过来
                    String scheme = getScheme(url);
                    String content = getContentUrl(url);
                    String type = getTypeFromUrl(url);
                    JSONObject data = getDataFromUrl(url);
                    if (PAGE_SETTING.equals(scheme)) {
                        // 获取文章标题
                        titleFromPage = JSON.parseObject(content).getString("article_title");
                        // 描述
                        descriptionFromPage = JSON.parseObject(content).getString("description");
                        // 分享的图片url
                        sharePicFromPage = JSON.parseObject(content).getString("share_pic");
                    }
                    return true;
                case ProtocolHeader.PROTOCOL_HEADER_WEIXIN:
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
            }

            return super.shouldOverrideUrlLoading(mWebview, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError
                error) {
            super.onReceivedError(view, request, error);
        }
    };

    private void showAlert(WebView view, String url, String message, final JsResult result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                        dialog.dismiss();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        result.cancel();
                    }
                });
        builder.show();
    }


    /**
     * 获得协议头
     *
     * @param url url
     * @return 协议头
     */
    private String getProtocolHeader(String url) {
        return url.substring(0, url.indexOf(":"));
    }


    /**
     * 获得Scheme
     *
     * @param url url
     * @return Scheme
     */
    private String getScheme(String url) {
        String[] splits = url.split("/");
        return splits.length > 1 ? splits[2] : null;
    }

    /**
     * 获得真实url内容
     *
     * @param url url
     * @return 真实url内容
     */
    public String getContentUrl(String url) {
        try {
            if (url.contains("{"))
                return URLDecoder.decode(url.substring(url.indexOf("{"), url.length()), "UTF-8");
            else
                return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTypeFromUrl(String url) {
        String[] split = url.split("/");
        return split.length > 3 ? split[3] : null;
    }

    /**
     * 把app里面的url加上inapp标志，网页端会通过此标志来判断是否是app里面调用
     *
     * @param url
     * @return
     */

    private JSONObject getDataFromUrl(String url) {
        if (url.contains("{")) {
            String str = url.substring(url.indexOf("{"));
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
            return jsonObject;
        } else
            return null;
    }


    private void appendUserAgent(String key, String value) {
        StringBuffer stringBuffer = new StringBuffer(mWebview.getSettings().getUserAgentString());
        stringBuffer.append(" weidu_" + key + "_" + value);
        mWebview.getSettings().setUserAgentString(stringBuffer.toString());
        Log.d(TAG, mWebview.getSettings().getUserAgentString());
    }


    /**
     * 协议头定义
     */
    public static final class ProtocolHeader {
        public static final String PROTOCOL_HEADER_HTTP = "http";
        public static final String PROTOCOL_HEADER_HTTPS = "https";
        public static final String PROTOCOL_HEADER_BAIDU = "baidu";
        public static final String PROTOCOL_HEADER_WEIXIN = "weixin";
    }

}
