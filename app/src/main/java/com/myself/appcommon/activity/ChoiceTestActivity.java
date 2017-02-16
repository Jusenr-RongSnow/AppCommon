package com.myself.appcommon.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.views.SquareCalendarView;
import com.myself.appcommon.R;
import com.myself.appcommon.base.BaseActivity;
import com.myself.appcommon.timePicket.TimePickerShow;
import com.myself.appcommon.timePicket.WheelHeightAndWeightHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChoiceTestActivity extends BaseActivity {
    public static final String TAG = ChoiceTestActivity.class.getSimpleName();

    private FrameLayout mFlView;
    private LinearLayout mLinearLayout;
    private TimePickerShow mTimePickerShow;
    private TextView mTvTime;
    private TextView mTvDialog;
    private TextView mTvDialog2;
    private TextView mTvTest;
    private Button mBtnTime;
    private Button mBtnDialog;
    private Button mBtnDialog2;
    private Button mBtnTest;
    private Button mBtnPopup;


    @Override
    public boolean needTranslucent() {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_choice_test);
    }

    @Override
    public void initStaticData() {
        mTimePickerShow = new TimePickerShow(this);
    }

    @Override
    public void initData() {
        mLinearLayout.addView(mTimePickerShow.timePickerView());
    }

    @Override
    public void initView() {
        mFlView = (FrameLayout) findViewById(R.id.fl_view);
        mLinearLayout = (LinearLayout) findViewById(R.id.date_view);

        mBtnTime = (Button) findViewById(R.id.btn_time);
        mTvTime = (TextView) findViewById(R.id.tv_time);

        mBtnDialog = (Button) findViewById(R.id.btn_dialog);
        mTvDialog = (TextView) findViewById(R.id.tv_dialog);

        mBtnTest = (Button) findViewById(R.id.btn_test);
        mTvTest = (TextView) findViewById(R.id.tv_test);

        mBtnDialog2 = (Button) findViewById(R.id.btn_dialog2);
        mTvDialog2 = (TextView) findViewById(R.id.tv_dialog2);

        mBtnPopup = (Button) findViewById(R.id.btn_popup);
    }

    @Override
    public void initListener() {
        final TextView view = new TextView(this);

        mBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTime.setText(mTimePickerShow.getTxtTime("-", "-", " ", ":", ":", ""));
            }
        });

        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WheelHeightAndWeightHandle(view);
                String[] random = getRandom();
                mTvTest.setText(random[0]);
            }
        });

        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerShow.timePickerAlertDialog(mTvDialog);
            }
        });

        mBtnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerShow.numberPickerAlertDialog(mTvDialog2);
            }
        });
        mBtnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(ChoiceTestActivity.this, mFlView);
            }
        });
    }


    private void showPopupWindow(final Context context, View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(R.layout.activity_square_calendar_view, null);
//        Calendar calendar = Calendar.getInstance();
//        int currYear = calendar.get(Calendar.YEAR);
//        int currMonth = calendar.get(Calendar.MONTH) + 1;
        List<CalendarInfo> list = new ArrayList<CalendarInfo>();
        SquareCalendarView squareMonthView = (SquareCalendarView) contentView.findViewById(R.id.squareMonthView);
        squareMonthView.setCalendarInfos(list);
        squareMonthView.setDateClick(new MonthView.IDateClick() {

            @Override
            public void onClickOnDate(int year, int month, int day) {
                Toast.makeText(context, year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
            }
        });
        // 设置按钮的点击事件
        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_left_btn_select));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }


    private String[] getRandom() {
        int random0 = new Random().nextInt(191) + 50;
        int random1 = new Random().nextInt(41) + 10;
        int random2 = new Random().nextInt(10);
        final String str0 = String.valueOf(random0) + "." + String.valueOf(random2);
        final String str1 = String.valueOf(random1) + "." + String.valueOf(random2);
        Log.e(TAG, "str0=" + str0);
        Log.e(TAG, "str1=" + str1);
        ArrayList<String> objects = new ArrayList<>();
        objects.add(0, str0);

        String[] strings = {str0, str1};

        return strings;
    }
}
