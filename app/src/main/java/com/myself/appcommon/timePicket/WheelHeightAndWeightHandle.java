package com.myself.appcommon.timePicket;

import android.text.TextUtils;
import android.view.View;

import com.myself.appcommon.R;

/**
 * Description: 身高、体重滚动选择器
 * Copyright  : Copyright (c) 2016
 * Email      : jusenr@163.com
 * Company    : 葡萄科技
 * Author     : Jusenr
 * Date       : 2017/2/10 18:47.
 */

public class WheelHeightAndWeightHandle {

    private View view;
    private WheelView wv_integer;
    private WheelView wv_decimal;
    private WheelView wv_company;

    private int START_INTEGER = 0, END_INTEGER;


    public WheelHeightAndWeightHandle(View view) {
        super();
        this.view = view;
        setView(view);
//        initDateTimePicker();
    }

    public WheelHeightAndWeightHandle(View view, boolean hasSelect) {
        super();
        this.view = view;
        setView(view);
    }

    /**
     * 初始化身高选择控件
     *
     * @param integer
     * @param decimal
     * @param company
     */
    public void initHeightAndWeightPicker(int integer, int decimal, String company) {
        // 添加数据并将其转换为list,方便之后的判断
//        List<Integer> list_integers = new ArrayList<>();
//        List<Integer> list_decimal = new ArrayList<>();
//
//        for (int i = START_INTEGER; i <= START_INTEGER; i++) {
//            list_integers.add(i);
//        }
//        Log.e(TAG, "initPicker: " + list_integers.toString());//50~240
//        for (int i = 0; i < 10; i++) {
//            list_decimal.add(i);
//        }
//        Log.e(TAG, "initPicker: " + list_decimal.toString());//0~9

        wv_integer = (WheelView) view.findViewById(R.id.wv_0);
        wv_decimal = (WheelView) view.findViewById(R.id.wv_1);
        wv_company = (WheelView) view.findViewById(R.id.wv_2);

        // 整数部分
        if (integer != -1) {
            wv_integer.setAdapter(new NumericWheelAdapter(START_INTEGER, END_INTEGER));
            wv_integer.setCyclic(false);// 不可循环滚动
            wv_integer.setLabel(".");//单位
            wv_integer.setCurrentItem(integer - START_INTEGER);// 初始化时显示的数据
        } else {
            wv_integer.setVisibility(View.GONE);
        }

        // 小数部分
        if (decimal != -1) {
            wv_decimal.setAdapter(new NumericWheelAdapter(0, 9));
            wv_decimal.setCyclic(false);// 不可循环滚动
            wv_decimal.setLabel("");//单位
            wv_decimal.setCurrentItem(decimal);
        } else {
            wv_decimal.setVisibility(View.GONE);
        }

        // 单位
        if (company != null) {
            wv_company.setCyclic(false);// 不可循环滚动
            wv_company.setLabel(company);
//            wv_company.setCurrentItem(0);
        } else {
            wv_company.setVisibility(View.GONE);
        }
    }

    /**
     * 获得选中身高
     *
     * @param strInteger 间开符号1
     * @param strDecimal 间开符号2
     * @param strInteger 间开符号3
     * @returndecimal
     */
    public String getHeightAndWeightData(String strInteger, String strDecimal, String strCompany) {
        StringBuffer sb = new StringBuffer();
        String integer = "";
        String decimal = "";
        String company = "";

        if (wv_integer.getVisibility() != View.GONE) {
            integer = String.valueOf(wv_integer.getCurrentItem() + START_INTEGER);
            integer = new StringBuffer(integer + strInteger).toString();
        }

        if (wv_decimal.getVisibility() != View.GONE) {
            decimal = String.valueOf(wv_decimal.getCurrentItem());
            decimal = new StringBuffer(decimal + strDecimal).toString();
        }

        if (wv_company.getVisibility() != View.GONE) {
//            company = String.valueOf(wv_company.getCurrentItem());
//            Log.e(TAG, "getHeightData: company=" + company);
            String label = wv_company.getLabel();
            label = TextUtils.isEmpty(label) ? "" : label;
            company = new StringBuffer(label + strCompany).toString();
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
