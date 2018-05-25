package com.camacuasoft.jazzfestivalapp.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.camacuasoft.jazzfestivalapp.Fragments.CalInsideFragment;

import java.util.Date;
import java.util.List;

/**
 * Created by nosotros on 24/06/2017.
 */

public class CalendarPageAdapter extends FragmentStatePagerAdapter{

    private int numbreOfTabs;
    private List<Date> dateList;

    public CalendarPageAdapter(FragmentManager fm, int numbreOfTabs, List<Date> dateList) {
        super(fm);
        this.numbreOfTabs = numbreOfTabs;
        this.dateList = dateList;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new CalInsideFragment();
        Bundle bund = new Bundle();
        bund.putSerializable("date", dateList.get(position));
        fragment.setArguments(bund);
        return fragment;
    }

    @Override
    public int getCount() {
        return numbreOfTabs;
    }
}
