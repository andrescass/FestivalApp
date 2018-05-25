package com.camacuasoft.jazzfestivalapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.camacuasoft.jazzfestivalapp.Fragments.FacebookFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.InstagramFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.TwitterFragment;

/**
 * Created by nosotros on 01/07/2017.
 */

public class SocialMediaPageAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public SocialMediaPageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FacebookFragment();
            case 2:
                return new InstagramFragment();
            case 1:
                return new TwitterFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
