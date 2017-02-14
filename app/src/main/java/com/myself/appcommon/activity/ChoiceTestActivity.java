package com.myself.appcommon.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myself.appcommon.R;
import com.myself.appcommon.base.BaseActivity;
import com.myself.appcommon.timePicket.TimePickerShow;
import com.myself.appcommon.timePicket.WheelHeightAndWeightView;

import java.util.Random;

public class ChoiceTestActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private TextView dateText1;
    private TextView dateText2;
    private Button getTime;
    private Button alertDialogBtn;
    private Button test;
    private TimePickerShow timePickerShow;


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
        timePickerShow = new TimePickerShow(this);
    }

    @Override
    public void initData() {
        linearLayout.addView(timePickerShow.timePickerView());
    }

    @Override
    public void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.date_view);
        dateText1 = (TextView) findViewById(R.id.txt_date1);
        dateText2 = (TextView) findViewById(R.id.txt_date2);
        getTime = (Button) findViewById(R.id.get_time);
        alertDialogBtn = (Button) findViewById(R.id.alertdialog);
        test = (Button) findViewById(R.id.test);
    }

    @Override
    public void initListener() {
        final TextView view = new TextView(this);

        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateText1.setText(timePickerShow.getTxtTime("-", "-", " ", ":", ":", ""));
            }
        });
        alertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerShow.timePickerAlertDialog(dateText2);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WheelHeightAndWeightView(view);
                getRandom();
            }
        });
    }


    private void getRandom() {
        double random0 = Math.random() * 100;
        double random1 = Math.random() * 10;
        final String str0 = String.valueOf(random0).split("\\.")[0];
        final String str1 = String.valueOf(random1).split("\\.")[0];
        final String str = str0 + "." + str1;
        System.out.println(str0);
        System.out.println(str1);
        int nextInt = new Random().nextInt(51);
        System.out.println(nextInt);
        final int parseInt = Integer.parseInt(str0);
        if (parseInt > 49)
            dateText2.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_time:

                break;

            case R.id.alertdialog:

                break;
        }
    }
}
