package com.dsw.calendar;

import android.util.Log;

import com.dsw.calendar.utils.DateUtils;

import static com.dsw.calendar.R.id.year;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Email      : jusenr@163.com
 * Company    : 葡萄科技
 * Author     : Jusenr
 * Date       : 2017/2/17 19:23.
 */

public class Test {

    public static void getLastMonthDays() {
        int currMonth = 8;
        int lastDays;
        for (int i = 0; i < 11; i++) {

            if (currMonth == 0) {//若果是1月份，上个月则为12月份
                lastDays = DateUtils.getMonthDays(year, 0);
            } else {
                lastDays = DateUtils.getMonthDays(year, (11 + currMonth) % 12);
            }

            Log.e("####", "getLastMonthDays: " + lastDays);
        }
    }
}
