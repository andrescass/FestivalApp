package com.camacuasoft.jazzfestivalapp.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class ArtistDetailActivity extends AppCompatActivity {

    private YouTubePlayerFragment playerFragment;
    private YouTubePlayer mPlayer;
    private String apiKey = "AIzaSyBrBtUaXveMv_50y5zQZnwkDB3rp-7oAms";

    Realm realm;
    RealmResults<Artist> artistList;
    int currentArtistID;
    Artist currentArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        Intent intent = getIntent();
        currentArtistID = intent.getIntExtra("artistID", 0);

        realm = Realm.getDefaultInstance();
        artistList = realm.where(Artist.class).equalTo("ID", currentArtistID).findAll();
        currentArtist = artistList.get(0);

        //** Bind views
        final ImageView artistPhoto = (ImageView) findViewById(R.id.artist_detail_act_image);
        TextView artistName = (TextView) findViewById(R.id.artist_detail_act_name);
        TextView artistBio = (TextView) findViewById(R.id.artist_detail_act_bio);
        final FrameLayout actFrame = (FrameLayout) findViewById(R.id.artist_act_frame);

        playerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_player_fragment);

        playerFragment.initialize(apiKey, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                mPlayer = youTubePlayer;

                //Enables automatic control of orientation
                mPlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

                //Show full screen in landscape mode always
                mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);

                //System controls will appear automatically
                mPlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

                if (!b) {
                    //player.cueVideo("9rLZYyMbJic");
                    mPlayer.loadVideo("eynnYLXW3Fo");
                }
                else
                {
                    mPlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                mPlayer = null;
            }
        });

        //Picasso.with(getContext()).load(currentArtist.getPhotoRes()).resize(artistPhoto.getWidth(), finalHeight).centerCrop().into(artistPhoto);
        artistName.setText(currentArtist.getName());
        artistBio.setText(currentArtist.getBio());

        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(this.getResources(), currentArtist.getPhotoResInt(), opt);
        int imageWidth = opt.outWidth;
        int imageHeight = opt.outHeight;
        int finalHeight = Math.round((float)FestivalMainApp.finalWidth/imageWidth*imageHeight);
        artistPhoto.getLayoutParams().height = finalHeight;
        Uri imageUri = Uri.fromFile(new File(currentArtist.getPhotoResUri()));
        //File image = new File(currentArtist.getPhotoResUri());
        String pathpath = "file:"+File.separator+File.separator+currentArtist.getPhotoResUri();
        Picasso.with(this).load(pathpath).resize(FestivalMainApp.finalWidth, FestivalMainApp.finalHeight).centerCrop().into(artistPhoto);

        //** Banner
        final ImageView artistBanner = (ImageView) findViewById(R.id.artist_detail_act_banner);
        Random bannerrand = new Random();
        final int bannerRes = FestivalMainApp.bannerList.get(bannerrand.nextInt(4));
        final BitmapFactory.Options optBan = new BitmapFactory.Options();
        optBan.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(this.getResources(), bannerRes, optBan);
        int banImageWidth = optBan.outWidth;
        int banImageHeight = optBan.outHeight;
        int banFinalHeight = Math.round((float)FestivalMainApp.finalWidth/banImageWidth*banImageHeight);
        Picasso.with(this).load(bannerRes).resize(FestivalMainApp.finalWidth, banFinalHeight).centerCrop().into(artistBanner);

    }
}
