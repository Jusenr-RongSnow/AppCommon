package com.myself.appcommon.timePicket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.myself.appcommon.R;
import com.myself.appcommon.alertdialog.IOSAlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimePickerShow {

    private Context context;
    private WheelMain wheelMain;
    private WheelheightView mWheelheightView;

    public TimePickerShow(Context context) {
        super();
        this.context = context;
    }

    public String getData() {
        return mWheelheightView.getData("");
    }

    public View heightPickerView(String dateStr) {
        View heightPickerView = View.inflate(context, R.layout.timepicker, null);
        mWheelheightView = new WheelheightView(heightPickerView);

        mWheelheightView.setEND_INTEGER(300);
        mWheelheightView.setSTART_INTEGER(50);
        // 若为空显示当前时间
        if (dateStr != null && !dateStr.equals("")) {
            mWheelheightView.initDateTimePicker(120, 5, "cm");
        } else {
            mWheelheightView.initDateTimePicker(120, 5, "cm");
        }

        return heightPickerView;
    }

    /**
     * 获得选中的时间
     *
     * @param strYear   间隔符号
     * @param strMon
     * @param strDay
     * @param strHour
     * @param strMins
     * @param strSecond
     * @return
     */
    public String getTxtTime(String strYear, String strMon, String strDay, String strHour, String strMins, String strSecond) {
        return wheelMain.getTime(strYear, strMon, strDay, strHour, strMins, strSecond);
    }

    public View timePickerView() {
        View timepickerview = View.inflate(context, R.layout.timepicker, null);
        wheelMain = new WheelMain(timepickerview);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        wheelMain.setEND_YEAR(year);// 设置最大年份
        wheelMain.initDateTimePicker(year, month, day, hour, min, second);

        return timepickerview;
    }

    /**
     * 时间选择控件
     *
     * @param dateStr 需显示的日期
     * @return
     */
    public View timePickerView(String dateStr) {
        View timepickerview = View.inflate(context, R.layout.timepicker, null);
        wheelMain = new WheelMain(timepickerview);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // int min = calendar.get(Calendar.MINUTE);
        // int second = calendar.get(Calendar.SECOND);
        wheelMain.setEND_YEAR(year);
        // 若为空显示当前时间
        if (dateStr != null && !dateStr.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(dateStr);
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year, month, day, -1, -1, -1);// 传-1表示不显示
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            wheelMain.initDateTimePicker(year, month, day, -1, -1, -1);
        }
        return timepickerview;
    }

    /**
     * @param
     * @return
     */
    public View headView() {
        View view = View.inflate(context, R.layout.headview, null);
        return view;
    }

    /**
     * alertDialog时间选择
     *
     * @param textView
     */
    public void timePickerAlertDialog(final TextView textView) {
        IOSAlertDialog dialog = new IOSAlertDialog(context);
        dialog.builder();
//        dialog.setTitle("选择日期");
        dialog.setHeadView(headView());
        dialog.setButtonStyle(0);
        dialog.setView(heightPickerView(textView.getText().toString()));
        dialog.setNegativeButton("", new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.setPositiveButton("完成", new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(getTxtTime("-", "-", " ", ":", ":", ""));
                Toast.makeText(context, getData(), Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
