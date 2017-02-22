package com.myself.appcommon.calendar.timessquare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dsw.calendar.timessquare.CalendarPickerView;
import com.dsw.calendar.utils.CalendarUtil;
import com.myself.appcommon.R;
import com.myself.appcommon.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SampleTimesSquareActivity extends Activity {
    private static final String TAG = "SampleTimesSquareActivity";
    private CalendarPickerView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_times_square);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(new Date());

        final Button single = (Button) findViewById(R.id.button_single);
        final Button multi = (Button) findViewById(R.id.button_multi);
        final Button range = (Button) findViewById(R.id.button_range);
        final Button dialog = (Button) findViewById(R.id.button_dialog);
        final Button done = (Button) findViewById(R.id.done_button);

        single.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                single.setEnabled(false);
                multi.setEnabled(true);
                range.setEnabled(true);

                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                        .withSelectedDate(new Date());
            }
        });

        multi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                single.setEnabled(true);
                multi.setEnabled(false);
                range.setEnabled(true);

                Calendar today = Calendar.getInstance();
                ArrayList<Date> dates = new ArrayList<Date>();
                for (int i = 0; i < 5; i++) {
                    today.add(Calendar.DAY_OF_MONTH, 3);
                    dates.add(today.getTime());
                }
                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                        .withSelectedDates(dates);
            }
        });

        range.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                single.setEnabled(true);
                multi.setEnabled(true);
                range.setEnabled(false);

                Calendar today = Calendar.getInstance();
                ArrayList<Date> dates = new ArrayList<Date>();
                today.add(Calendar.DATE, 3);
                dates.add(today.getTime());
                today.add(Calendar.DATE, 5);
                dates.add(today.getTime());
                calendar.init(new Date(), nextYear.getTime()) //
                        .inMode(CalendarPickerView.SelectionMode.RANGE) //
                        .withSelectedDates(dates);
            }
        });

        dialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarPickerView dialogView =
                        (CalendarPickerView) getLayoutInflater().inflate(R.layout.dialog, null, false);
                dialogView.init(new Date(), nextYear.getTime());
                new AlertDialog.Builder(SampleTimesSquareActivity.this)
                        .setTitle("I'm a dialog!")
                        .setView(dialogView)
                        .setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });

        done.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Long longLime = calendar.getSelectedDate().getTime();
                Calendar instance = Calendar.getInstance();
                Date date = new Date(longLime);
                instance.setTime(date);
                Log.e("####", "onClick: " + longLime);

                String time = CalendarUtil.getDay(instance);
                Log.d("####", "CalendarUtil--" + time);

                String time1 = DateUtils.millisecondToDate(longLime, DateUtils.YMD_PATTERN);
                Log.d("####", "DateUtils--" + time1);

                Toast.makeText(SampleTimesSquareActivity.this, time, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
