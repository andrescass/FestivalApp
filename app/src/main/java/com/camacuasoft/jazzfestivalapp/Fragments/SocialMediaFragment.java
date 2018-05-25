package com.camacuasoft.jazzfestivalapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camacuasoft.jazzfestivalapp.Adapters.SocialMediaPageAdapter;
import com.camacuasoft.jazzfestivalapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialMediaFragment extends Fragment {


    public SocialMediaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_media, container, false);

        //** Tab layout and adapter
        TabLayout socialTab = (TabLayout) view.findViewById(R.id.social_tabs);
        socialTab.addTab(socialTab.newTab().setIcon(R.drawable.social_facebook_box_blue));
        //socialTab.addTab(socialTab.newTab().setIcon(R.drawable.instagram_logo));
        socialTab.addTab(socialTab.newTab().setIcon(R.drawable.twitter_logo));

        socialTab.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager socialPager = (ViewPager) view.findViewById(R.id.social_pager);
        SocialMediaPageAdapter socialPagerAdapter = new SocialMediaPageAdapter(getChildFragmentManager(), socialTab.getTabCount());
        socialPager.setAdapter(socialPagerAdapter);
        socialPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(socialTab));

        socialTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                socialPager.setCurrentItem(position);
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

}
