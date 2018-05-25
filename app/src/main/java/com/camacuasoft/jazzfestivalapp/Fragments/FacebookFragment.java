package com.camacuasoft.jazzfestivalapp.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.camacuasoft.jazzfestivalapp.Adapters.FacebookListAdapter;
import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.R;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment {

    String userID = "1860163514310134";
    String userSecret = "28f01c8230bdc99e9d95625dac81e610";

    JSONArray data;

    Dialog photoDialog;


    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_facebook, container, false);
        FacebookSdk.setClientToken("1860163514310134|0Pg4sIyEWzs_gxozdQA1X_enVp4");
        List<String> perms = new ArrayList<>();
        perms.add("client_credentials");
        AccessToken.setCurrentAccessToken(new AccessToken("1860163514310134|0Pg4sIyEWzs_gxozdQA1X_enVp4","1860163514310134", "1860163514310134", perms, null, null, null, null));

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "elgatoylacaja/posts",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        try {
                            if(response.getRawResponse() != null) {
                                JSONObject jobj = new JSONObject(response.getRawResponse());
                                data = jobj.getJSONArray("data");
                                //Toast.makeText(getContext(), data.getJSONObject(0).getString("message"), Toast.LENGTH_SHORT).show();
                                RecyclerView faceList = (RecyclerView) view.findViewById(R.id.facebook_recycler_view);
                                FacebookListAdapter faceAdapter = new FacebookListAdapter(data, R.layout.facebook_list_item,
                                        new FacebookListAdapter.OnPhotoClickListener() {
                                            @Override
                                            public void photoClikListener(String imageuri) {
                                                photoDialog = new Dialog(getActivity());
                                                photoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                photoDialog.setCancelable(false);
                                                photoDialog.setContentView(R.layout.face_photo_popup_layout);


                                                ImageView photoImage = (ImageView) photoDialog.findViewById(R.id.face_popup_image);
                                                //Picasso.with(getContext()).load(imageuri).fit().into(photoImage);
                                                Picasso.with(getContext()).load(imageuri).resize(FestivalMainApp.totalWidth, FestivalMainApp.totalHeight).centerInside().into(photoImage);

                                                photoImage.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        photoDialog.dismiss();
                                                    }
                                                });

                                                photoDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                    @Override
                                                    public void onCancel(DialogInterface dialogInterface) {
                                                        photoDialog.dismiss();
                                                    }
                                                });

                                                photoDialog.show();
                                            }
                                        });

                                faceList.setHasFixedSize(true);
                                faceList.setItemAnimator(new DefaultItemAnimator());
                                faceList.setLayoutManager(new LinearLayoutManager(getContext()));
                                faceList.setAdapter(faceAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();



        return view;
    }

}
