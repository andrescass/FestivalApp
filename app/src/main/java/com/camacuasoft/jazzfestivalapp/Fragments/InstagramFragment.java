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
public class InstagramFragment extends Fragment {


    public InstagramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instagram, container, false);

        return view;
    }

}
