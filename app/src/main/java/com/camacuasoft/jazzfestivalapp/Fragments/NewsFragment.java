package com.camacuasoft.jazzfestivalapp.Fragments;


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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.camacuasoft.jazzfestivalapp.Adapters.NewsReciclerAdapter;
import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.News;
import com.camacuasoft.jazzfestivalapp.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    Realm realm;
    RealmResults<Artist> artists;
    RealmResults<News> news;

    RecyclerView newsListView;
    NewsReciclerAdapter newsAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_news, container, false);

        realm = Realm.getDefaultInstance();

        //TODO take this out in final versions
        //** Creating dummy db content
        artists = realm.where(Artist.class).findAllSorted("ID");
        /*news = realm.where(News.class).findAllSorted("date");
        if((artists.size() == 0) || (news.size() == 0)) {
            Artist dummyArtist_1 = new Artist("Victor Wooden", R.drawable.victor_wooten, " ");
            Artist dummyArtist_2 = new Artist("Mike Stern", R.drawable.mike_stern, " ");

            Calendar dummyDate_1 = Calendar.getInstance();
            Calendar dummyDate_2 = Calendar.getInstance();
            dummyDate_1.set(2017, 6, 6, 21, 0);
            dummyDate_1.set(2017, 6, 8, 22, 30);
            String dummyInfo_1 = "Uno de los mejores bajistas de los últimos tiempos. Victor Wooten tendra su momento esperado por muchos en el cuadro del evento";
            String dummyInfo_2 = "Con su repertorio de temas increibles y la compañía de dos grandes amigos, esta promete ser la cereza del postre para una fecha mágica del \" La Plata Jazz Festival\"";
            News dummyNews_1 = new News(dummyDate_1.getTime(), "Victor Wooden en La Plata", dummyArtist_1, dummyInfo_1+dummyInfo_2);
            News dummyNews_2 = new News(dummyDate_2.getTime(), "Meet & Greet con Mike Stern", dummyArtist_2, dummyInfo_1+dummyInfo_2);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(dummyArtist_1);
            realm.copyToRealmOrUpdate(dummyArtist_2);
            realm.copyToRealmOrUpdate(dummyNews_1);
            realm.copyToRealmOrUpdate(dummyNews_2);
            realm.commitTransaction();
        }*/

        news = realm.where(News.class).findAllSorted("date");

        newsListView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        newsAdapter = new NewsReciclerAdapter(news, R.layout.news_item_layout, new NewsReciclerAdapter.OnMoreButtonClickListener() {
            @Override
            public void onMoreButtonClick(News news) {

            }
        });

        newsListView.setHasFixedSize(true);
        newsListView.setItemAnimator(new DefaultItemAnimator());
        newsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        newsListView.setAdapter(newsAdapter);

        final ScrollView newsScroll = (ScrollView) view.findViewById(R.id.news_scroll);

        final ImageView newsBanner = (ImageView) view.findViewById(R.id.news_banner);
        Random bannerrand = new Random();
        final int bannerRes = FestivalMainApp.bannerList.get(bannerrand.nextInt(4));
        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(), bannerRes, opt);
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int imageWidth = opt.outWidth;
                int imageHeight = opt.outHeight;
                int finalHeight = Math.round((float)newsBanner.getWidth()/imageWidth*imageHeight);
                Picasso.with(getContext()).load(bannerRes).resize(newsBanner.getWidth(), finalHeight).centerCrop().into(newsBanner);
                newsScroll.setPadding(0, 0, 0, finalHeight);
                return false;
            }
        });

        return view;
    }

}
