package com.myself.appcommon.calendarcomponent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.views.SquareCalendarView;
import com.myself.appcommon.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SquareCalendarActivity extends Activity {
    private SquareCalendarView squareMonthView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_calendar_view);
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        List<CalendarInfo> list = new ArrayList<CalendarInfo>();
        list.add(new CalendarInfo(currYear, currMonth, 6, "￥800"));
        list.add(new CalendarInfo(currYear, currMonth, 7, "￥1000", 2));
        list.add(new CalendarInfo(currYear, currMonth, 8, "￥1200", 1));
        squareMonthView = (SquareCalendarView) findViewById(R.id.squareMonthView);
        squareMonthView.setCalendarInfos(list);
        squareMonthView.setDateClick(new MonthView.IDateClick() {

            @Override
            public void onClickOnDate(int year, int month, int day) {
                Toast.makeText(SquareCalendarActivity.this, year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
