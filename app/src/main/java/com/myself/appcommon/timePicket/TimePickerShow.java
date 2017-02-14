package com.myself.appcommon.timePicket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.myself.appcommon.R;
import com.myself.appcommon.alertdialog.IOSAlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.myself.appcommon.activity.WebviewActivity.TAG;

@SuppressLint("SimpleDateFormat")
public class TimePickerShow {

    private Context context;
    private WheelMain wheelMain;
    private WheelHeightAndWeightView mWheelHeightAndWeightView;
    private View mView;

    public TimePickerShow(Context context) {
        super();
        this.context = context;
    }

    public String getHeightAndWeightData(String strInteger, String strDecimal, String strCompany) {
        return mWheelHeightAndWeightView.getHeightAndWeightData(strInteger, strDecimal, strCompany);
    }

    public View heightAndWeightPickerView(String dataStr, int startInteger, int endInteger, String company) {
        View mHeightAndWeightPickerView = View.inflate(context, R.layout.timepicker1, null);
        mWheelHeightAndWeightView = new WheelHeightAndWeightView(mHeightAndWeightPickerView);
        mWheelHeightAndWeightView.setSTART_INTEGER(startInteger);
        mWheelHeightAndWeightView.setEND_INTEGER(endInteger);
        // 若为空显示当前时间
        if (dataStr.contains(".") && !TextUtils.isEmpty(dataStr) && !"null".equals(dataStr)) {
            String[] split = dataStr.split("\\.");
            int s0 = Integer.parseInt(split[0]);
            int s1 = Integer.parseInt(split[1].substring(0, 1));
            mWheelHeightAndWeightView.initHeightAndWeightPicker(s0, s1, company);
        } else {
            mWheelHeightAndWeightView.initHeightAndWeightPicker(startInteger, 0, company);
        }

        return mHeightAndWeightPickerView;
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

        wheelMain.setEND_YEAR(2030);// 设置最大年份
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
        wheelMain.setEND_YEAR(2030);
        // 若为空显示当前时间
        if (dateStr.length() > 0 && !"null".equals(dateStr)) {
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
     * set headView
     *
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
        final IOSAlertDialog dialog = new IOSAlertDialog(context);
        final String toString = textView.getText().toString().trim();
        Log.e(TAG, "timePickerAlertDialog: toString=" + toString);
        //100.2cm  65.5cm   100.5kg 15.5kg
        if (toString.contains(".") && !TextUtils.isEmpty(toString) && !"null".equals(toString)) {
            String[] split = toString.split("\\.");
            int s0 = Integer.parseInt(split[0]);
            int s1 = Integer.parseInt(split[1].substring(0, 1));
            Log.e(TAG, "timePickerAlertDialog: \r\ns0=" + s0 + "\r\ns1=" + s1);
        }

        View view = headView();
        final TextView tv_height = (TextView) view.findViewById(R.id.tv_height);
        final TextView tv_weight = (TextView) view.findViewById(R.id.tv_weight);
        final ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
        mView = heightAndWeightPickerView(toString, 50, 240, "CM");

        tv_height.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_height.setTextColor(Color.parseColor("#8B49F6"));
                tv_weight.setTextColor(Color.parseColor("#959595"));
                iv_img.setImageResource(R.drawable.step_band_modal_dialogue_01);
                dialog.removeView(mView);
                mView = heightAndWeightPickerView(toString, 50, 240, "CM");
                dialog.setView(mView);
            }
        });
        tv_weight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_height.setTextColor(Color.parseColor("#959595"));
                tv_weight.setTextColor(Color.parseColor("#8B49F6"));
                iv_img.setImageResource(R.drawable.step_band_modal_dialogue_02);
                dialog.removeView(mView);
                mView = heightAndWeightPickerView(toString, 10, 50, "KG");
                dialog.setView(mView);
            }
        });

        dialog.builder();
//        dialog.setTitle("选择日期");
        dialog.setHeadView(view);
        dialog.setButtonStyle(0);
        dialog.setView(mView);
        dialog.setNegativeButton("", new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.setPositiveButton("完成", new OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.setText(getTxtTime("-", "-", " ", ":", ":", ""));
                textView.setText(getHeightAndWeightData(".", "", ""));
//                Toast.makeText(context, getData(""), Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
