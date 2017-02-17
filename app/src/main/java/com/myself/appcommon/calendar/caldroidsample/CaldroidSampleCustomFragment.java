package com.myself.appcommon.calendar.caldroidsample;

import com.dsw.calendar.caldroid.CaldroidFragment;
import com.dsw.calendar.caldroid.CaldroidGridAdapter;

public class CaldroidSampleCustomFragment extends CaldroidFragment {

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub
        return new CaldroidSampleCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }

}
