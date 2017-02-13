package com.myself.appcommon.timePicket;

import android.view.View;

import com.myself.appcommon.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Email      : jusenr@163.com
 * Company    : 葡萄科技
 * Author     : Jusenr
 * Date       : 2017/2/10 18:47.
 */

public class WheelheightView {

    private View view;
    private WheelView wv_integer;
    private WheelView wv_decimal;
    private WheelView wv_company;

    private int START_INTEGER = 50, END_INTEGER;


    public WheelheightView(View view) {
        super();
        this.view = view;
        setView(view);
//        initDateTimePicker();
    }

    public WheelheightView(View view, boolean hasSelect) {
        super();
        this.view = view;
        setView(view);
    }


    public void initDateTimePicker(int integer, int decimal, String company) {
        // 添加数据并将其转换为list,方便之后的判断
//        List<Integer> list_integers = new ArrayList<>();
//        List<Integer> list_decimal = new ArrayList<>();
//
//        for (int i = START_INTEGER; i <= END_INTEGER; i++) {
//            list_integers.add(i);
//        }
//        Log.e(TAG, "initDateTimePicker: " + list_integers.toString());//50~300
//        for (int i = 0; i < 10; i++) {
//            list_decimal.add(i);
//        }
//        Log.e(TAG, "initDateTimePicker: " + list_decimal.toString());//0~9

        wv_integer = (WheelView) view.findViewById(R.id.year);
        wv_decimal = (WheelView) view.findViewById(R.id.month);
        wv_company = (WheelView) view.findViewById(R.id.day);

        // 整数部分
        if (integer != -1) {
            wv_integer.setAdapter(new NumericWheelAdapter(50, 240));
            wv_integer.setCyclic(false);// 可循环滚动
            wv_integer.setLabel("");//单位
            wv_integer.setCurrentItem(integer /*+ START_INTEGER*/);
        } else {
            wv_integer.setVisibility(View.GONE);
        }

        // 小数部分
        if (integer != -1) {
            wv_decimal.setAdapter(new NumericWheelAdapter(0, 9));
            wv_decimal.setCyclic(false);// 可循环滚动
            wv_decimal.setLabel("");//单位
            wv_decimal.setCurrentItem(decimal);
        } else {
            wv_decimal.setVisibility(View.GONE);
        }

        // 单位
        if (company != null) {
            wv_company.setCyclic(false);// 不可循环滚动
            wv_company.setLabel("cm");
//            wv_company.setCurrentItem(0);
        } else {
            wv_company.setVisibility(View.GONE);
        }
    }

    /**
     * 获得选中时间
     *
     * @param str 间开符号
     * @returndecimal
     */
    public String getData(String str) {
        StringBuffer sb = new StringBuffer();
        String integer = "";
        String decimal = "";
        String company = "";

        if (wv_integer.getVisibility() != View.GONE) {
            integer = String.valueOf(wv_integer.getCurrentItem() + START_INTEGER);
            if (wv_integer.getCurrentItem() + 1 <= 9) {
                integer = new StringBuffer(integer).toString();
            }
            integer = new StringBuffer(integer + str).toString();
        }

        if (wv_decimal.getVisibility() != View.GONE) {
            decimal = String.valueOf(wv_decimal.getCurrentItem());
            if (wv_decimal.getCurrentItem() <= 9) {
                decimal = new StringBuffer(decimal).toString();
            }
            decimal = new StringBuffer(decimal + str).toString();
        }

        if (wv_decimal.getVisibility() != View.GONE) {
            company = "cm";
        }
        sb.append(integer).append(decimal).append(company);

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
