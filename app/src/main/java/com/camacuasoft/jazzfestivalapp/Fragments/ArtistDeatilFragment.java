package com.camacuasoft.jazzfestivalapp.Fragments;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistDeatilFragment extends Fragment {

    Realm realm;
    RealmResults<Artist> artistList;
    int currentArtistID;
    Artist currentArtist;

    // TODO: Replace with your client ID
    private static String CLIENT_ID = "75a724e1d67745fe93f7dc72ffc7ab70";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "jazzfestivalprotocol://callback";
    private static final int SPOTIFT_REQUEST_CODE = 1237;

//    private SpotifyPlayer mPlayer;
//    boolean isPlaying = true;
//    boolean isLoggedIn = false;
//    String playlistUri = "37i9dQZF1DX8KUmmjlkOod";
//    String playlistUser = "spotify";
//
//    TextView spotyText;
//    ImageButton spotyPlay;

    YouTubePlayerSupportFragment artistPlayerFrag;
    YouTubePlayer artistVideoPlayer;

    public ArtistDeatilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_artist_deatil, container, false);

        Bundle artistBundle = getArguments();
        if(artistBundle != null){
            currentArtistID = artistBundle.getInt("artistID");
        }

        realm = Realm.getDefaultInstance();
        artistList = realm.where(Artist.class).equalTo("ID", currentArtistID).findAll();
        currentArtist = artistList.get(0);

        //** Bind views
        final ImageView artistPhoto = (ImageView) view.findViewById(R.id.artist_detail_image);
        TextView artistName = (TextView) view.findViewById(R.id.artist_detail_name);
        TextView artistBio = (TextView) view.findViewById(R.id.artist_detail_bio);
        //spotyText = (TextView) view.findViewById(R.id.artist_detail_playlist);

        //ImageButton spotyPrev = (ImageButton) view.findViewById(R.id.artist_detail_prev);
        //spotyPlay = (ImageButton) view.findViewById(R.id.artist_detail_play);
        //ImageButton spotyNext = (ImageButton) view.findViewById(R.id.artist_detail_next);

        artistPlayerFrag = (YouTubePlayerSupportFragment) getFragmentManager().findFragmentById(R.id.artist_detail_youfrag);
        artistPlayerFrag.initialize("AIzaSyBrBtUaXveMv_50y5zQZnwkDB3rp-7oAms",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        String videoId = "eynnYLXW3Fo";
                        artistVideoPlayer = youTubePlayer;
                        artistVideoPlayer.cueVideo(videoId);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getContext().getResources(), currentArtist.getPhotoResInt(), opt);
        /*int imageWidth = opt.outWidth;
        int imageHeight = opt.outHeight;
        int imageViewWidth = artistPhoto.getMeasuredWidth();
        float scale = (float)artistPhoto.getWidth()/imageWidth;
        int finalHeight = Math.round(scale*imageHeight);
        //artistPhoto.getLayoutParams().height = finalHeight;
        //artistPhoto.requestLayout();*/

        //Picasso.with(getContext()).load(currentArtist.getPhotoRes()).resize(artistPhoto.getWidth(), finalHeight).centerCrop().into(artistPhoto);
        artistName.setText(currentArtist.getName());
        artistBio.setText(currentArtist.getBio());

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int imageWidth = opt.outWidth;
                int imageHeight = opt.outHeight;
                int imageViewWidth = artistPhoto.getMeasuredWidth();
                float scale = (float)artistPhoto.getWidth()/imageWidth;
                int finalHeight = Math.round(scale*imageHeight);
                //artistPhoto.getLayoutParams().height = finalHeight;
                //artistPhoto.requestLayout();

                Uri imageUri = Uri.parse(currentArtist.getPhotoResUri());
                //Picasso.with(getContext()).load(currentArtist.getPhotoRes()).resize(artistPhoto.getWidth(), finalHeight).centerCrop().into(artistPhoto);
                Picasso.with(getContext()).load(imageUri).resize(artistPhoto.getWidth(), finalHeight).centerCrop().into(artistPhoto);
                return false;
            }
        });

//        spotyText.setMovementMethod(new ScrollingMovementMethod());
//        spotyPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mPlayer != null && mPlayer.isInitialized()) {
//                    if (isPlaying) {
//                        mPlayer.pause(new Player.OperationCallback() {
//                            @Override
//                            public void onSuccess() {
//                                isPlaying = false;
//                                spotyPlay.setImageResource(R.drawable.ic_action_play);
////                                if(mPlayer.getMetadata().currentTrack.name != null)
////                                    spotyText.setText(mPlayer.getMetadata().currentTrack.name);
//                            }
//
//                            @Override
//                            public void onError(Error error) {
//
//                            }
//                        });
//                    } else {
//                        mPlayer.resume(new Player.OperationCallback() {
//                            @Override
//                            public void onSuccess() {
//                                isPlaying = true;
//                                spotyPlay.setImageResource(R.drawable.ic_action_pause);
////                                if(mPlayer.getMetadata().currentTrack.name != null)
////                                    spotyText.setText(mPlayer.getMetadata().currentTrack.name);
//                            }
//
//                            @Override
//                            public void onError(Error error) {
//
//                            }
//                        });
//                    }
//                } else {
//                    spotiPlayPressed();
//                }
//            }
//        });
//
//        spotyPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mPlayer != null && mPlayer.isInitialized()) {
//                    mPlayer.skipToPrevious(new Player.OperationCallback() {
//                        @Override
//                        public void onSuccess() {
////                            if(mPlayer.getMetadata().currentTrack.name != null)
////                                spotyText.setText(mPlayer.getMetadata().currentTrack.name);
//                        }
//
//                        @Override
//                        public void onError(Error error) {
//
//                        }
//                    });
//                }
//            }
//        });
//
//        spotyNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mPlayer != null && mPlayer.isInitialized()) {
//                    mPlayer.skipToNext(new Player.OperationCallback() {
//                        @Override
//                        public void onSuccess() {
////                            if(mPlayer.getMetadata().currentTrack.name != null)
////                                spotyText.setText(mPlayer.getMetadata().currentTrack.name);
//                        }
//
//                        @Override
//                        public void onError(Error error) {
//
//                        }
//                    });
//                }
//            }
//        });

        return view;
    }

//    void spotiPlayPressed() {
//        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
//                AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
//        builder.setScopes(new String[]{"user-read-private", "streaming", "playlist-read-private"});
//        builder.setShowDialog(true);
//        AuthenticationRequest request = builder.build();
//        //AuthenticationClient.openLoginActivity(getActivity(), SPOTIFT_REQUEST_CODE, request);
//        Intent intent = AuthenticationClient.createLoginActivityIntent(getActivity(), request);
//        startActivityForResult(intent, SPOTIFT_REQUEST_CODE);
//        //AuthenticationClient.openLoginInBrowser(getActivity(), request);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //Toast.makeText(getContext(), "Activity result", Toast.LENGTH_SHORT).show();
//
//        if(requestCode == SPOTIFT_REQUEST_CODE) {
//            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);
//            //Toast.makeText(getContext(), "Activity result 2", Toast.LENGTH_SHORT).show();
//            if(response.getType() == AuthenticationResponse.Type.TOKEN) {
//                Config playerConfig = new Config(getContext(), response.getAccessToken(), CLIENT_ID);
//                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
//                    @Override
//                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
//                        mPlayer = spotifyPlayer;
//                        mPlayer.addConnectionStateCallback(ArtistDeatilFragment.this);
//                        mPlayer.addNotificationCallback(ArtistDeatilFragment.this);
//                        //Toast.makeText(getContext(), "Initialized", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
//                        //Toast.makeText(getContext(), "Activity result error", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//            else {
//                //Toast.makeText(getContext(), "Activity result " + response.getType().toString(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    public void onLoggedIn() {
//        //Toast.makeText(getContext(), "Logged in", Toast.LENGTH_SHORT).show();
//        mPlayer.playUri(null, "spotify:user:" + playlistUser + ":playlist:" + playlistUri, 0, 0); //playlist:" + playlistUri track:2TpxZ7JUBn3uw46aR7qd6V"
//        spotyPlay.setImageResource(R.drawable.ic_action_pause);
//        //if(mPlayer != null && mPlayer.getMetadata() != null && mPlayer.getMetadata().currentTrack != null && mPlayer.getMetadata().currentTrack.name != null)
//        //    spotyText.setText(mPlayer.getMetadata().currentTrack.name);
//    }
//
//    @Override
//    public void onLoggedOut() {
//        //Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onLoginFailed(Error error) {
//        //Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onTemporaryError() {
//        //Toast.makeText(getContext(), "Temporary error", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onConnectionMessage(String s) {
//        //Toast.makeText(getContext(), "Connection message " + s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPlaybackEvent(PlayerEvent playerEvent) {
//        //Toast.makeText(getContext(), "Player event", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPlaybackError(Error error) {
//        //Toast.makeText(getContext(), "Playback error " + error.toString(), Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onDestroy() {
//        Spotify.destroyPlayer(this);
        super.onDestroy();
    }
}
