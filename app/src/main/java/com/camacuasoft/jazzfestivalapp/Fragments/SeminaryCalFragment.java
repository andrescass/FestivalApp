package com.camacuasoft.jazzfestivalapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camacuasoft.jazzfestivalapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeminaryCalFragment extends Fragment {


    public SeminaryCalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seminary_cal, container, false);



        return view;
    }

}
