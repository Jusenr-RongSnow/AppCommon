package com.myself.appcommon.timePicket;

import android.view.View;

import com.myself.appcommon.R;

/**
 * Description: 步数滚动选择器
 * Copyright  : Copyright (c) 2016
 * Email      : jusenr@163.com
 * Company    : 葡萄科技
 * Author     : Jusenr
 * Date       : 2017/2/10 18:47.
 */

public class StepHandle {
    public static final String TAG = StepHandle.class.getSimpleName();

    private View view;
    private WheelView wv_step;

    private int START_INTEGER = 1, END_INTEGER;


    public StepHandle(View view) {
        super();
        this.view = view;
        setView(view);
//        initStepPicker(30, "");
    }

    public StepHandle(View view, boolean hasSelect) {
        super();
        this.view = view;
        setView(view);
    }

    /**
     * 初始化步数选择控件
     *
     * @param integer
     */
    public void initStepPicker(int integer) {
        // 添加数据并将其转换为list,方便之后的判断
//        List<Integer> list_integers = new ArrayList<>();
//        for (int i = 1; i <= 30; i++) {
//            list_integers.add(i);
//        }
//        Log.e(TAG, "initPicker: " + list_integers.toString());//1~30

        wv_step = (WheelView) view.findViewById(R.id.wv_0);

        if (integer != -1) {
            wv_step.setAdapter(new NumerStepWheelAdapter(START_INTEGER, END_INTEGER));
            wv_step.setCyclic(false);// 不可循环滚动
            int i = integer - START_INTEGER;
            wv_step.setLabel("");//单位
            wv_step.setCurrentItem(i);// 初始化时显示的数据
        } else {
            wv_step.setVisibility(View.GONE);
        }
    }

    /**
     * 获得选中步数
     *
     * @param strInteger 间开符号1
     * @returndecimal
     */
    public String getStepData(String strInteger) {
        StringBuffer sb = new StringBuffer();
        String integer = "";

        if (wv_step.getVisibility() != View.GONE) {
            integer = String.valueOf(wv_step.getCurrentItem() + START_INTEGER);
            int i = Integer.valueOf(integer) * 1000;
            integer = String.valueOf(i);
            integer = new StringBuffer(integer + strInteger).toString();
        }
        sb.append(integer);

        return sb.toString();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public int getSTART_INTEGER() {
        return START_INTEGER;
    }

    public void setSTART_INTEGER(int START_INTEGER) {
        this.START_INTEGER = START_INTEGER;
    }

    public int getEND_INTEGER() {
        return END_INTEGER;
    }

    public void setEND_INTEGER(int END_INTEGER) {
        this.END_INTEGER = END_INTEGER;
    }
}
