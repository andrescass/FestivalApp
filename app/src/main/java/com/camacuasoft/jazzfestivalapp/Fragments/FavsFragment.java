package com.camacuasoft.jazzfestivalapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camacuasoft.jazzfestivalapp.Adapters.FavsAdapter;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Favorites;
import com.camacuasoft.jazzfestivalapp.Models.Show;
import com.camacuasoft.jazzfestivalapp.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavsFragment extends Fragment {

    Realm realm;
    RealmResults<Artist> artistList;
    RealmResults<Favorites> favList;
    RealmResults<Show> showlist;

    RecyclerView favsRecycler;
    FavsAdapter favsAdapter;


    public FavsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favs, container, false);

        realm = Realm.getDefaultInstance();
        artistList = realm.where(Artist.class).equalTo("isFavorite", true).findAllSorted("ID");
        showlist = realm.where(Show.class).findAllSorted("date");

        favsRecycler = (RecyclerView) view.findViewById(R.id.favs_recycler_view);
        favsAdapter = new FavsAdapter(artistList, showlist, R.layout.artist_list_item,
                new FavsAdapter.OnFavBtnClickListener() {
                    @Override
                    public void onFavBtnClik(Artist artist) {
                        onFavBtnClicked(artist);
                    }
                },
                new FavsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemSelected(Artist artist) {

                    }
                });

        favsRecycler.setHasFixedSize(true);
        favsRecycler.setItemAnimator(new DefaultItemAnimator());
        favsRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        favsRecycler.setAdapter(favsAdapter);

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

}
