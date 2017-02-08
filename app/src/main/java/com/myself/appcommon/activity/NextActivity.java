package com.myself.appcommon.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myself.appcommon.R;
import com.myself.appcommon.base.BaseActivity;

public class NextActivity extends BaseActivity {

    private Button mBtnBack;
    private TextView mTvText;

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
        mTvText.setText("你好啊~~");
    }

    @Override
    public void initView() {
        setStatusBarAlpha(0);
        setCanSlideBack(true);
        mTvText = (TextView) findViewById(R.id.tv_text);
        mBtnBack = (Button) findViewById(R.id.btn_back);
    }

    @Override
    public void initListener() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBackActivity();
            }
        });
    }
}
