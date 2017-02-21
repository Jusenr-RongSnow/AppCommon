package com.dsw.calendar.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.dsw.calendar.theme.DefaultDayTheme;
import com.dsw.calendar.utils.DateUtils;

/**
 * Created by Administrator on 2016/8/7.
 */
public class CircleMonthView extends MonthView {

    public CircleMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawLines(Canvas canvas, int rowsCount) {
        int rightX = getWidth();
        Path path;
        float startX = 0;
        float endX = rightX;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(theme.colorLine());
        for (int row = 1; row <= rowsCount; row++) {
            float startY = row * rowSize;
            path = new Path();
            path.moveTo(startX, startY);
            path.lineTo(endX, startY);
            canvas.drawPath(path, paint);
        }
    }

    @Override
    protected void drawBG(Canvas canvas, int column, int row, int day) {
        if (day == selDay) {
            //绘制背景色矩形
            float startRecX = columnSize * column + 1;
            float startRecY = rowSize * row + 1;
            float endRecX = startRecX + columnSize - 2 * 1;
            float endRecY = startRecY + rowSize - 2 * 1;
            float cx = (startRecX + endRecX) / 2;
            float cy = (startRecY + endRecY) / 2;
            float radius = columnSize < rowSize ? columnSize / 2 : rowSize / 2;
            paint.setColor(theme.colorSelectBG());
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx, cy, radius, paint);
        }
    }

    @Override
    protected void drawDecor(Canvas canvas, int column, int row, int year, int month, int day) {
        if (calendarInfos != null && calendarInfos.size() > 0) {
            if (TextUtils.isEmpty(iscalendarInfo(year, month, day))) return;
            paint.setColor(theme.colorDecor());
            paint.setStyle(Paint.Style.FILL);
            float circleX = (float) (columnSize * column + columnSize * 0.8);
            float circleY = (float) (rowSize * row + rowSize * 0.2);
            canvas.drawCircle(circleX, circleY, theme.sizeDecor(), paint);
        }
    }

    @Override
    protected void drawRest(Canvas canvas, int column, int row, int year, int month, int day) {
        //不实现，暂时不考虑何种表示方案
    }

    @Override
    protected void drawCurrentMonthText(Canvas canvas, int column, int row, int year, int month, int day) {
        paint.setTextSize(theme.sizeDay());
        float startX = columnSize * column + (columnSize - paint.measureText(day + "")) / 2;
        float startY = rowSize * row + rowSize / 2 - (paint.ascent() + paint.descent()) / 2;
        paint.setStyle(Paint.Style.STROKE);
        String des = iscalendarInfo(year, month, day);
        if (day == selDay) {//日期为选中的日期
            if (!TextUtils.isEmpty(des)) {//desc不为空的时候
                int dateY = (int) (startY - 10);
                paint.setColor(theme.colorSelectDay());
                canvas.drawText(day + "", startX, dateY, paint);

                paint.setTextSize(theme.sizeDesc());
                int priceX = (int) (columnSize * column + (columnSize - paint.measureText(des)) / 2);
                int priceY = (int) (startY + 15);
                canvas.drawText(des, priceX, priceY, paint);
            } else {//des为空的时候
                paint.setColor(theme.colorSelectDay());
                canvas.drawText(day + "", startX, startY, paint);
            }
        } else if (day == currDay && currDay != selDay && currMonth == selMonth) {//今日的颜色，不是选中的时候
            //正常月，选中其他日期，则今日为红色
            paint.setColor(theme.colorToday());
            canvas.drawText(day + "", startX, startY, paint);
        } else {
            if (!TextUtils.isEmpty(des)) {//没选中，但是desc不为空
                int dateY = (int) (startY - 10);
                paint.setColor(theme.colorWeekday());
                canvas.drawText(day + "", startX, dateY, paint);

                paint.setTextSize(theme.sizeDesc());
                paint.setColor(theme.colorDesc());
                int priceX = (int) (columnSize * column + Math.abs((columnSize - paint.measureText(des)) / 2));
                int priceY = (int) (startY + 15);
                canvas.drawText(des, priceX, priceY, paint);
            } else {//des为空
                paint.setColor(theme.colorWeekday());
                canvas.drawText(day + "", startX, startY, paint);
            }
        }
    }

    @Override
    protected void drawLastMonthText(Canvas canvas, int column, int row, int year, int month, int day) {
        int weekNumber = DateUtils.getFirstDayWeek(year, month);//当前月份1号对应的星期几
        int mLastMonthDays = weekNumber - 1;//当前月份可视上个月的天数
        int lastMonth, lastDays;
        if (month > 0) {
            lastMonth = month - 1;
            lastDays = DateUtils.getMonthDays(year, lastMonth % 12);
        } else {
            lastMonth = 11;
            lastDays = 31;
        }
        int mLastMonthStartDay = lastDays - mLastMonthDays + 1;

        if (mLastMonthDays != 0) {
            for (int i = mLastMonthStartDay; i <= lastDays; i++) {
                paint.setTextSize(theme.sizeDay());
                float startX = columnSize * column + (columnSize - paint.measureText(mLastMonthStartDay + "")) / 2;
                float startY = rowSize * row + rowSize / 2 - (paint.ascent() + paint.descent()) / 2;
                paint.setStyle(Paint.Style.STROKE);
                String des = iscalendarInfo(year, lastMonth, mLastMonthStartDay);

//                Log.e(TAG, "drawNextMonthText: " + lastMonth + "--" + mLastMonthStartDay);

                paint.setColor(theme.colorOtherMonth());
                canvas.drawText(mLastMonthStartDay + "", startX, startY, paint);

                column++;
                mLastMonthStartDay++;
            }
        }
    }

    @Override
    protected void drawNextMonthText(Canvas canvas, int column, int row, int year, int month, int day) {
        int nextMonth;
        if (month < 11) {
            nextMonth = month + 1;
        } else {
            nextMonth = 1;
        }
        int nextNumber = DateUtils.getFirstDayWeek(year, nextMonth);//当前月份页下个月1号对应的星期几
        int mNextMonthDays = 7 - nextNumber + 1;//当前月份可视下个月的天数

        int mNextMonthStartDay = 1;

        if (mNextMonthDays != 0) {
            for (int i = mNextMonthStartDay; i <= mNextMonthDays; i++) {
                paint.setTextSize(theme.sizeDay());
                float startX = columnSize * column + (columnSize - paint.measureText(mNextMonthStartDay + "")) / 2;
                float startY = rowSize * row + rowSize / 2 - (paint.ascent() + paint.descent()) / 2;
                paint.setStyle(Paint.Style.STROKE);
                String des = iscalendarInfo(year, nextMonth, mNextMonthStartDay);

//                Log.e(TAG, "drawNextMonthText: " + nextMonth + "--" + mNextMonthStartDay);

                paint.setColor(theme.colorOtherMonth());
                canvas.drawText(mNextMonthStartDay + "", startX, startY, paint);

                column++;
                mNextMonthStartDay++;
            }
        }
    }

    @Override
    protected void createTheme() {
        theme = new DefaultDayTheme();
    }
}
