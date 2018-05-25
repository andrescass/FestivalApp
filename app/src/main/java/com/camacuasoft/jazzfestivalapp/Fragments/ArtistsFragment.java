package com.camacuasoft.jazzfestivalapp.Fragments;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.camacuasoft.jazzfestivalapp.Activities.ArtistDetailActivity;
import com.camacuasoft.jazzfestivalapp.Adapters.ArtistRecyclerAdapter;
import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Favorites;
import com.camacuasoft.jazzfestivalapp.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistsFragment extends Fragment {

    Realm realm;
    RealmResults<Artist> artistList;
    RealmResults<Favorites> favList;

    RecyclerView artistRecycvler;
    ArtistRecyclerAdapter artistAdapter;



    public ArtistsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_artists, container, false);

        realm = Realm.getDefaultInstance();

        //** Get Artist and fav lists
        artistList = realm.where(Artist.class).findAllSorted("ID");

        //** Check which artist is in favorites
        for (Artist currArtist:artistList) {
            favList = realm.where(Favorites.class).equalTo("ID", currArtist.getID()).findAll();
            if(favList.size() > 0){
                realm.beginTransaction();
                currArtist.setFavorite(true);
                realm.copyToRealmOrUpdate(currArtist);
                realm.commitTransaction();
            } else {
                realm.beginTransaction();
                currArtist.setFavorite(false);
                realm.copyToRealmOrUpdate(currArtist);
                realm.commitTransaction();
            }

        }

        artistRecycvler = (RecyclerView) view.findViewById(R.id.artists_recycler_view);
        artistAdapter = new ArtistRecyclerAdapter(artistList, R.layout.artist_list_item,
                new ArtistRecyclerAdapter.OnFavBtnClickListener() {
                    @Override
                    public void onFavBtnClik(Artist artist) {
                        onFavBtnClicked(artist);
                    }
                },
                new ArtistRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemSelected(Artist artist) {
                        onItemClicked(artist);
                    }
                });

        artistRecycvler.setHasFixedSize(true);
        artistRecycvler.setItemAnimator(new DefaultItemAnimator());
        artistRecycvler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        artistRecycvler.setAdapter(artistAdapter);

        final ScrollView artistScroll = (ScrollView) view.findViewById(R.id.artist_list_scroll);

        //** Banner
        final ImageView artistBanner = (ImageView) view.findViewById(R.id.artist_list_banner);
        Random bannerrand = new Random();
        final int bannerRes = FestivalMainApp.bannerList.get(bannerrand.nextInt(4));
        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(), bannerRes, opt);
        int imageWidth = opt.outWidth;
        int imageHeight = opt.outHeight;
        int finalHeight = Math.round((float)FestivalMainApp.finalWidth/imageWidth*imageHeight);
        Picasso.with(getContext()).load(bannerRes).resize(FestivalMainApp.finalWidth, finalHeight).centerCrop().into(artistBanner);
        artistScroll.setPadding(0, 0, 0, finalHeight);



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
        //Bundle artistBundle = new Bundle();
        //artistBundle.putInt("artistID", artist.getID());
        /*Fragment artistDetailFragment = new ArtistDeatilFragment();
        artistDetailFragment.setArguments(artistBundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, artistDetailFragment)
                .addToBackStack("artistList")
                .commit();*/
        Intent intent = new Intent(getContext(), ArtistDetailActivity.class);
        intent.putExtra("artistID", artist.getID());
        startActivity(intent);

    }

}
