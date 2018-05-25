package com.camacuasoft.jazzfestivalapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camacuasoft.jazzfestivalapp.Adapters.InfoRecyclerAdapter;
import com.camacuasoft.jazzfestivalapp.Models.Info;
import com.camacuasoft.jazzfestivalapp.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    Realm realm;
    RealmResults<Info> infos;

    RecyclerView infoListView;
    InfoRecyclerAdapter infoAdapter;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        realm = Realm.getDefaultInstance();
        infos = realm.where(Info.class).findAll();

        infoListView = (RecyclerView) view.findViewById(R.id.info_recycler_view);
        infoAdapter = new InfoRecyclerAdapter(infos, R.layout.news_item_layout);

        infoListView.setHasFixedSize(true);
        infoListView.setItemAnimator(new DefaultItemAnimator());
        infoListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        infoListView.setAdapter(infoAdapter);

        return view;
    }

}
