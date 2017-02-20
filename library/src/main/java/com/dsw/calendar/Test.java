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
        boolean s = true;
        int currMonth = 2;
        int lastDays;
//        if (s) {
//            for (int i = 0; i < 12; i++) {
//                lastDays = DateUtils.getMonthDays(year, i % 12);
//                Log.e("####", "getLastMonthDays: " + i + "--" + lastDays);
//            }
//        } else {
//            lastDays = DateUtils.getMonthDays(year, currMonth % 12);
//            Log.e("####", "getLastMonthDays: " + currMonth + "--" + lastDays);
//        }

        for (int i = 0; i < 12; i++) {
            currMonth = i;
//            lastDays = DateUtils.getMonthDays(year, currMonth % 12);
            if (currMonth > 0) {
                lastDays = DateUtils.getMonthDays(year, currMonth - 1 % 12);
            } else {
                lastDays = 31;
            }

            Log.e("####", "getLastMonthDays: " +  currMonth++ + "--" + currMonth + "--" + lastDays);
        }
    }
}
