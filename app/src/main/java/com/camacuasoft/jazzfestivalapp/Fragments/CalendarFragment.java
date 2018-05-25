package com.camacuasoft.jazzfestivalapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camacuasoft.jazzfestivalapp.Adapters.CalendarPageAdapter;
import com.camacuasoft.jazzfestivalapp.Models.Show;
import com.camacuasoft.jazzfestivalapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    Realm realm;
    RealmResults<Show> showList;

    List<Date> showDates;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        realm = Realm.getDefaultInstance();
        showList = realm.where(Show.class).findAllSorted("date");

        //** Get different show dates
        showDates = new ArrayList<>();
        int dateNumber = 0;
        for (Show iterShow:showList) {

            Calendar showCal = Calendar.getInstance();
            showCal.setTime(iterShow.getDate());

            if(showDates.size() == 0) {
                showDates.add(iterShow.getDate());
                dateNumber++;
            } else {
                Calendar lastDate = Calendar.getInstance();
                lastDate.setTime(showDates.get(dateNumber-1));
                if((showCal.get(Calendar.YEAR) != lastDate.get(Calendar.YEAR)) ||
                        (showCal.get(Calendar.MONTH) != lastDate.get(Calendar.MONTH)) ||
                        (showCal.get(Calendar.DAY_OF_MONTH) != lastDate.get(Calendar.DAY_OF_MONTH))) {
                    showDates.add(iterShow.getDate());
                    dateNumber++;
                }
            }
        }

        //** Tab layout and adapter
        TabLayout calendarTab = (TabLayout) view.findViewById(R.id.calendar_tabs);
        final SimpleDateFormat calTabFormat = new SimpleDateFormat("MMM, dd", Locale.US);
        for(int i=0; i<showDates.size(); i++){
            calendarTab.addTab(calendarTab.newTab().setText(calTabFormat.format(showDates.get(i))));
        }
        calendarTab.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager calendarPager = (ViewPager) view.findViewById(R.id.cal_pager);
        CalendarPageAdapter pagerAdapter= new CalendarPageAdapter(getChildFragmentManager(), calendarTab.getTabCount(), showDates);
        calendarPager.setAdapter(pagerAdapter);
        calendarPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(calendarTab));

        calendarTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                calendarPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        for (Fragment fragment : getFragmentManager().getFragments()) {
            if (fragment instanceof CalInsideFragment ) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(fragment);
                ft.attach(fragment);
                ft.commit();
                //Toast.makeText(getContext(), "on Resume" + fragment.toString(), Toast.LENGTH_LONG);
            }
        }
    }

}
