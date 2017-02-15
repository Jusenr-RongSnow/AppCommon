package com.myself.appcommon.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myself.appcommon.R;
import com.myself.appcommon.base.BaseActivity;
import com.myself.appcommon.timePicket.TimePickerShow;
import com.myself.appcommon.timePicket.WheelHeightAndWeightHandle;

import java.util.ArrayList;
import java.util.Random;

public class ChoiceTestActivity extends BaseActivity {
    public static final String TAG = ChoiceTestActivity.class.getSimpleName();

    private LinearLayout mLinearLayout;
    private TimePickerShow mTimePickerShow;
    private TextView mTvTime;
    private TextView mTvDialog;
    private TextView mTvDialog2;
    private TextView mTvTest;
    private Button mBtnTime;
    private Button mBtnDialog;
    private Button mBtnDialog2;
    private Button mBtnTest;


    @Override
    public boolean needTranslucent() {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_choice_test);
    }

    @Override
    public void initStaticData() {
        mTimePickerShow = new TimePickerShow(this);
    }

    @Override
    public void initData() {
        mLinearLayout.addView(mTimePickerShow.timePickerView());
    }

    @Override
    public void initView() {
        mLinearLayout = (LinearLayout) findViewById(R.id.date_view);

        mBtnTime = (Button) findViewById(R.id.btn_time);
        mTvTime = (TextView) findViewById(R.id.tv_time);

        mBtnDialog = (Button) findViewById(R.id.btn_dialog);
        mTvDialog = (TextView) findViewById(R.id.tv_dialog);

        mBtnTest = (Button) findViewById(R.id.btn_test);
        mTvTest = (TextView) findViewById(R.id.tv_test);

        mBtnDialog2 = (Button) findViewById(R.id.btn_dialog2);
        mTvDialog2 = (TextView) findViewById(R.id.tv_dialog2);

    }

    @Override
    public void initListener() {
        final TextView view = new TextView(this);

        mBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTime.setText(mTimePickerShow.getTxtTime("-", "-", " ", ":", ":", ""));
            }
        });

        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WheelHeightAndWeightHandle(view);
                String[] random = getRandom();
                mTvTest.setText(random[0]);
            }
        });

        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerShow.timePickerAlertDialog(mTvDialog);
            }
        });

        mBtnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerShow.numberPickerAlertDialog(mTvDialog2);
            }
        });
    }

    private String[] getRandom() {
        int random0 = new Random().nextInt(191) + 50;
        int random1 = new Random().nextInt(41) + 10;
        int random2 = new Random().nextInt(10);
        final String str0 = String.valueOf(random0) + "." + String.valueOf(random2);
        final String str1 = String.valueOf(random1) + "." + String.valueOf(random2);
        Log.e(TAG, "str0=" + str0);
        Log.e(TAG, "str1=" + str1);
        ArrayList<String> objects = new ArrayList<>();
        objects.add(0, str0);

        String[] strings = {str0, str1};

        return strings;
    }
}
