package com.myself.appcommon.activity;

import com.myself.appcommon.R;
import com.myself.appcommon.base.BaseActivity;

public class NextActivity extends BaseActivity {

    @Override
    public boolean needTranslucent() {
        return true;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_next);
    }

    @Override
    public void initStaticData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void initView() {
        setStatusBarAlpha(0);
        setCanSlideBack(true);
    }

    @Override
    public void initListener() {
        // TODO Auto-generated method stub

    }

}
