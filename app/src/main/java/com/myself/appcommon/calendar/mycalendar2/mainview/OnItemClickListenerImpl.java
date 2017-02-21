package com.myself.appcommon.calendar.mycalendar2.mainview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.myself.appcommon.calendar.mycalendar2.adapter.CalendarAdapter;

public class OnItemClickListenerImpl implements OnItemClickListener {

    private CalendarAdapter adapter = null;
    private Calendar2Activity activity = null;

    public OnItemClickListenerImpl(CalendarAdapter adapter, Calendar2Activity activity) {
        this.adapter = adapter;
        this.activity = activity;
    }

    public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
        if (activity.currList.get(position).isThisMonth() == false) {
            return;
        }
        adapter.setSelectedPosition(position);
        adapter.notifyDataSetInvalidated();
        activity.lastSelected = activity.currList.get(position).getDate();
    }

}
