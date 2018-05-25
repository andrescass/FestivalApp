package com.camacuasoft.jazzfestivalapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camacuasoft.jazzfestivalapp.Activities.ArtistDetailActivity;
import com.camacuasoft.jazzfestivalapp.Adapters.CalInsideAdapter;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Favorites;
import com.camacuasoft.jazzfestivalapp.Models.Show;
import com.camacuasoft.jazzfestivalapp.R;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalInsideFragment extends Fragment {

    Realm realm;
    RealmResults<Show> showList;
    RealmResults<Favorites> favList;



    public CalInsideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cal_inside, container, false);

        realm = Realm.getDefaultInstance();
        Bundle bund = getArguments();
        Date showDate = (Date) bund.getSerializable("date");
        showList = realm.where(Show.class).equalTo("date", showDate).findAllSorted("date");

        RecyclerView calInRecycle = (RecyclerView) view.findViewById(R.id.cal_inside_recycler);
        CalInsideAdapter calInsideAdapter = new CalInsideAdapter(showList, R.layout.calendar_item,
                new CalInsideAdapter.OnFavBtnClickListener() {
                    @Override
                    public void onFavBtnClik(Artist artist) {
                        onFavBtnClicked(artist);
                    }
                },
                new CalInsideAdapter.OnArtistClickListener() {
                    @Override
                    public void onArtistClick(Artist artist) {
                        onItemClicked(artist);
                    }
                });

        calInRecycle.setHasFixedSize(true);
        calInRecycle.setItemAnimator(new DefaultItemAnimator());
        calInRecycle.setLayoutManager(new LinearLayoutManager(this.getContext()));
        calInRecycle.setAdapter(calInsideAdapter);

        return view;
    }

    void onFavBtnClicked(Artist artist) {
        if(artist.isFavorite()){
            realm.beginTransaction();
            artist.setFavorite(false);
            favList = realm.where(Favorites.class).equalTo("ID", artist.getID()).findAll();
            if(favList.size() > 0) {
                favList.get(0).deleteFromRealm();
            }
            realm.copyToRealmOrUpdate(artist);
            realm.commitTransaction();
        } else {
            realm.beginTransaction();
            artist.setFavorite(true);
            favList = realm.where(Favorites.class).equalTo("ID", artist.getID()).findAll();
            if(favList.size() == 0) {
                Favorites newFav = new Favorites(artist);
                realm.copyToRealmOrUpdate(newFav);
            }
            realm.copyToRealmOrUpdate(artist);
            realm.commitTransaction();
        }
    }

    void onItemClicked(Artist artist){
        /*Bundle artistBundle = new Bundle();
        artistBundle.putInt("artistID", artist.getID());
        Fragment artistDetailFragment = new ArtistDeatilFragment();
        artistDetailFragment.setArguments(artistBundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, artistDetailFragment)
                .addToBackStack("calendar")
                .commit();*/

        Intent intent = new Intent(getContext(), ArtistDetailActivity.class);
        intent.putExtra("artistID", artist.getID());
        startActivity(intent);
    }

}
