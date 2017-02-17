package com.myself.appcommon.calendar.mycalendar;

import java.util.Calendar;

/**
 * 日历控件样式绘制类
 *
 * @Description: 日历控件样式绘制类
 * @Author Hanyonglu
 * @Date 2012-3-18 下午03:33:42
 */
public class DayStyle {
    private final static String[] vecStrWeekDayNames = getWeekDayNames();

    private static String[] getWeekDayNames() {
        String[] vec = new String[10];

        vec[Calendar.SUNDAY] = "日";
        vec[Calendar.MONDAY] = "一";
        vec[Calendar.TUESDAY] = "二";
        vec[Calendar.WEDNESDAY] = "三";
        vec[Calendar.THURSDAY] = "四";
        vec[Calendar.FRIDAY] = "五";
        vec[Calendar.SATURDAY] = "六";

        return vec;
    }

    public static String getWeekDayName(int iDay) {
        return vecStrWeekDayNames[iDay];
    }

    public static int getWeekDay(int index, int iFirstDayOfWeek) {
        int iWeekDay = -1;

        if (iFirstDayOfWeek == Calendar.MONDAY) {
            iWeekDay = index + Calendar.MONDAY;

            if (iWeekDay > Calendar.SATURDAY)
                iWeekDay = Calendar.SUNDAY;
        }

        if (iFirstDayOfWeek == Calendar.SUNDAY) {
            iWeekDay = index + Calendar.SUNDAY;
        }

        return iWeekDay;
    }
}
