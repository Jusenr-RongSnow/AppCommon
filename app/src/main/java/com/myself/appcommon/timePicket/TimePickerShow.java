package com.myself.appcommon.timePicket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myself.appcommon.R;
import com.myself.appcommon.alertdialog.IOSAlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


@SuppressLint("SimpleDateFormat")
public class TimePickerShow {
    public static final String TAG = TimePickerShow.class.getSimpleName();

    private Context context;
    private WheelTimeHandle mWheelTimeHandle;
    private WheelHeightAndWeightHandle mWheelHeightAndWeightHandle;
    private StepHandle mStepHandle;
    private View mView;

    private IOSAlertDialog dialog;
    private TextView mTvHeight;
    private TextView mTvWeight;
    private ImageView mIvImg;

    public TimePickerShow(Context context) {
        super();
        this.context = context;
    }

    public String getHeightAndWeightData(String strInteger, String strDecimal, String strCompany) {
        return mWheelHeightAndWeightHandle.getHeightAndWeightData(strInteger, strDecimal, strCompany);
    }

    public View heightAndWeightPickerView(String dataStr, int startInteger, int endInteger, String company) {
        View mHeightAndWeightPickerView = View.inflate(context, R.layout.timepicker1, null);
        mWheelHeightAndWeightHandle = new WheelHeightAndWeightHandle(mHeightAndWeightPickerView);
        mWheelHeightAndWeightHandle.setSTART_INTEGER(startInteger);
        mWheelHeightAndWeightHandle.setEND_INTEGER(endInteger);
        // 若为空显示当前时间
        if (dataStr.contains(".") && !TextUtils.isEmpty(dataStr) && !"null".equals(dataStr)) {
            String[] split = dataStr.split("\\.");
            int s0 = Integer.parseInt(split[0]);
            int s1 = Integer.parseInt(split[1].substring(0, 1));
            mWheelHeightAndWeightHandle.initHeightAndWeightPicker(s0, s1, company);
        } else {
            mWheelHeightAndWeightHandle.initHeightAndWeightPicker(startInteger, 0, company);
        }

        return mHeightAndWeightPickerView;
    }

    public String getStepData(String strInteger) {
        return mStepHandle.getStepData(strInteger);
    }


    public View stepPickerView(String dataStr, int startInteger, int endInteger, String company) {
        View mView = View.inflate(context, R.layout.timepicker2, null);
        mStepHandle = new StepHandle(mView);
        mStepHandle.setSTART_INTEGER(startInteger);
        mStepHandle.setEND_INTEGER(endInteger);

        // 若为空显示当前时间
        if (!TextUtils.isEmpty(dataStr) && !"null".equals(dataStr)) {
            int s0 = Integer.parseInt(dataStr);
            mStepHandle.initStepPicker(s0, company);
        } else {
            mStepHandle.initStepPicker(startInteger, company);
        }

        return mView;
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
        return mWheelTimeHandle.getTime(strYear, strMon, strDay, strHour, strMins, strSecond);
    }

    public View timePickerView() {
        View timepickerview = View.inflate(context, R.layout.timepicker, null);
        mWheelTimeHandle = new WheelTimeHandle(timepickerview);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        mWheelTimeHandle.setEND_YEAR(2030);// 设置最大年份
        mWheelTimeHandle.initDateTimePicker(year, month, day, hour, min, second);

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
        mWheelTimeHandle = new WheelTimeHandle(timepickerview);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // int min = calendar.get(Calendar.MINUTE);
        // int second = calendar.get(Calendar.SECOND);
        mWheelTimeHandle.setEND_YEAR(2030);
        // 若为空显示当前时间
        if (dateStr.length() > 0 && !"null".equals(dateStr)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(dateStr);
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                mWheelTimeHandle.initDateTimePicker(year, month, day, -1, -1, -1);// 传-1表示不显示
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            mWheelTimeHandle.initDateTimePicker(year, month, day, -1, -1, -1);
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
     * alertDialog
     *
     * @param textView
     */
    public void numberPickerAlertDialog(final TextView textView) {
        if (dialog == null)
            dialog = new IOSAlertDialog(context);
        String toString = textView.getText().toString().trim();
        Log.e(TAG, "toString=" + toString);

        final String str = getRandom(10, 100, false);

        View view = headView();
        TextView mTvSetting = (TextView) view.findViewById(R.id.tv_setting);
        TextView mTvDesc = (TextView) view.findViewById(R.id.tv_desc);
        ImageView mIvPicture = (ImageView) view.findViewById(R.id.iv_picture);
        RelativeLayout mRlOther = (RelativeLayout) view.findViewById(R.id.rl_other);
        mRlOther.setVisibility(View.GONE);
        mTvSetting.setText("运动目标设置");
        mTvDesc.setText("根据中国孩子日常所需运动量推荐");
        mIvPicture.setImageResource(R.drawable.img_band_modal_dialogue_60_02);

//        mView = heightAndWeightPickerView(str, 50, 240, "CM");
        mView = stepPickerView(str, 10, 100, "");

        dialog.builder();
        dialog.setHeadView(view);
        dialog.setView(mView);

        /**
         * 完成
         */
        dialog.setPositiveButton("完成", new OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = getStepData("");
//                String data = getHeightAndWeightData(".", "", "");
                Log.e(TAG, "onClick: 完成weightData=" + data);
                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                textView.setText(data);
            }
        });

        dialog.show();
    }

    /**
     * alertDialog
     *
     * @param textView
     */
    public void timePickerAlertDialog(final TextView textView) {
        if (dialog == null)
            dialog = new IOSAlertDialog(context);
        final StringBuffer stringBuffer = new StringBuffer();
        String toString = textView.getText().toString().trim();
        Log.e(TAG, "toString=" + toString);

        final String str0 = getRandom(50, 240, true);
        final String str1 = getRandom(10, 50, true);

        View view = headView();
        mTvHeight = (TextView) view.findViewById(R.id.tv_height);
        mTvWeight = (TextView) view.findViewById(R.id.tv_weight);
        mIvImg = (ImageView) view.findViewById(R.id.iv_img);
        mView = heightAndWeightPickerView(str0, 50, 240, "CM");

        mTvHeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                upTask(str0);
//                dialog.setLayout(true);
            }
        });
        mTvWeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                downTask(str1);
//                dialog.setLayout(false);
            }
        });

        dialog.builder();
        dialog.setHeadView(view);
        dialog.setView(mView);

        /**
         * 上一步
         */
        dialog.setPreviousStepButton("", new OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBuffer.delete(0, stringBuffer.length());
                upTask(str0);
            }
        });

        /**
         * 下一步
         */
        dialog.setNextStepButton("下一步", new OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightData = getHeightAndWeightData(".", "", "");
                Log.e(TAG, "onClick: 下一步heightData=" + heightData);
                stringBuffer.append(heightData);
                downTask(str1);
            }
        });

        /**
         * 完成
         */
        dialog.setCompleteButton("完成", new OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.setText(getTxtTime("-", "-", " ", ":", ":", ""));
                String weightData = getHeightAndWeightData(".", "", "");
                Log.e(TAG, "onClick: 完成weightData=" + weightData);
                stringBuffer.append(weightData);
                Toast.makeText(context, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                textView.setText(stringBuffer.toString());
                Log.e(TAG, "onClick: " + stringBuffer.toString());
            }
        });

//        dialog.setNegativeButton("取消", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        dialog.setPositiveButton("确认", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String weightData = getHeightAndWeightData(".", "", "");
//                Toast.makeText(context, weightData, Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onClick: 确认weightData=" + weightData);
//            }
//        });

        dialog.show();
    }

    private void upTask(final String s) {
        mTvHeight.setTextColor(Color.parseColor("#8B49F6"));
        mTvWeight.setTextColor(Color.parseColor("#959595"));
        mIvImg.setImageResource(R.drawable.step_band_modal_dialogue_01);
        dialog.removeView(mView);
        mView = heightAndWeightPickerView(s, 50, 240, "CM");
        dialog.setView(mView);
    }

    private void downTask(final String s) {
        mTvHeight.setTextColor(Color.parseColor("#959595"));
        mTvWeight.setTextColor(Color.parseColor("#8B49F6"));
        mIvImg.setImageResource(R.drawable.step_band_modal_dialogue_02);
        dialog.removeView(mView);
        mView = heightAndWeightPickerView(s, 10, 50, "KG");
        dialog.setView(mView);
    }

    private String getRandom(int min, int max, boolean isCombinedDecimal) {
        int random = new Random().nextInt(max - min) + min;
        String str0 = String.valueOf(random);

        int index = new Random().nextInt(10);
        String str1 = str0 + "." + String.valueOf(index);

        Log.e("getRandom-->", "str0=" + str0);
        Log.e("getRandom-->", "str1=" + str1);

        return isCombinedDecimal ? str1 : str0;
    }
}