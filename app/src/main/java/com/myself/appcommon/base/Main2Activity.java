package com.myself.appcommon.base;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myself.appcommon.R;

import butterknife.BindView;
import butterknife.OnClick;

public class Main2Activity extends BasicActivity {

    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.ll_0)
    LinearLayout mLl0;

    private String s;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void initData() {
        s = "Hello......";
    }

    @Override
    public void initView() {
        mTv1.setText(s);
        mTv2.setText("你好啊-3......");
        mTv3.setText("真笨-3......");
    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.ll_0})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_2:
                Toast.makeText(mContext, "你好啊", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_3:
                break;
            case R.id.ll_0:
                break;
        }
    }
}
