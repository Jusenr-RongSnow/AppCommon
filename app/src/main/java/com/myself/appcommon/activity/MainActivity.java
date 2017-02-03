package com.myself.appcommon.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends BaseActivity implements HttpDataResponse, ThemeSettingsHelper.ThemeCallback {

    private Button mBtn;

    private ThemeSettingsHelper mThemeSettingsHelper;

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

    }

    @Override
    public void initView() {
        mThemeSettingsHelper = ThemeSettingsHelper.getThemeSettingsHelper(this);
        mThemeSettingsHelper.changeTheme(this, ThemeSettingsHelper.THEME_NIGHT);
        setStatusBarAlpha(0);
        mBtn = (Button) findViewById(R.id.test_send_request);
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

}
