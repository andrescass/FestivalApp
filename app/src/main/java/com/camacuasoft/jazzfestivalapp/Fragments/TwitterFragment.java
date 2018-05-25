package com.camacuasoft.jazzfestivalapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.camacuasoft.jazzfestivalapp.R;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;

import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwitterFragment extends Fragment {


    public TwitterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_twitter, container, false);

        TwitterConfig twitterConfig = new TwitterConfig.Builder(getContext())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("mHThvn9cZx2cY1rfSvmwXGSCA", "BpoKgNsCLWzp997PdnbfPuZA8vIw3Penqp5ZNVSuNvi88PE0eO"))
                .debug(true)
                .build();
        Twitter.initialize(twitterConfig);

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        SearchService statusesService = twitterApiClient.getSearchService();
        //Call<Tweet> call = statusesService.tweets("https://api.twitter.com/1.1/search/tweets.json?=#haiku", null);
        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#Boca OR #Alemania")
                .build();
        TweetTimelineListAdapter searchAdapter = new TweetTimelineListAdapter.Builder(getContext())
                .setTimeline(searchTimeline)
                .setViewStyle(R.style.tw__TweetDarkStyle)
                .build();

        ListView twitterListV = (ListView) view.findViewById(R.id.twitter_list);
        twitterListV.setAdapter(searchAdapter);
        

        return view;
    }

}
