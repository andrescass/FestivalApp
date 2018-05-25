package com.camacuasoft.jazzfestivalapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nosotros on 01/07/2017.
 */

public class FacebookListAdapter extends RecyclerView.Adapter<FacebookListAdapter.ViewHolder> {

    private JSONArray postList;
    private int layout;
    private OnPhotoClickListener onPhotoClickListener;

    private Context context;

    public FacebookListAdapter(JSONArray postList, int layout, OnPhotoClickListener onPhotoClickListener){
        this.postList = postList;
        this.layout = layout;
        this.onPhotoClickListener = onPhotoClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.Bind(postList.getJSONObject(position));
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return postList.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fbPostPicture;
        TextView fbPostTitle;
        TextView fbPostDate;
        TextView fbPostText;

        public ViewHolder(View itemView) {
            super(itemView);
            fbPostPicture = (ImageView) itemView.findViewById(R.id.facebook_item_picture);
            fbPostTitle = (TextView) itemView.findViewById(R.id.facebook_item_title);
            fbPostDate = (TextView) itemView.findViewById(R.id.facebook_item_time);
            fbPostText = (TextView) itemView.findViewById(R.id.facebook_item_text);
        }

        public void Bind(JSONObject jobj) throws JSONException {
            String objectId = "";
            String provImageUri;
            fbPostText.setText(jobj.getString("message"));

            //** Handle time and date
            SimpleDateFormat givenFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String givenPostDate = jobj.get("created_time").toString();
            try {
                Date givenDate = givenFormat.parse(givenPostDate);
                SimpleDateFormat desiredFormat = new SimpleDateFormat("dd-MMM-yyyy', 'HH:mm");
                fbPostDate.setText(desiredFormat.format(givenDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //provImageUri = jobj.getString("source");
            objectId = jobj.getString("id");

            String[] objectIdSplit = objectId.split("_");

            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + objectIdSplit[1] + "?fields=source",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            if(response.getRawResponse() != null) {
                                final String imageUri;
                                try {
                                    JSONObject jobj = new JSONObject(response.getRawResponse());
                                    imageUri = jobj.getString("source");
                                    Picasso.with(context).load(imageUri).resize(FestivalMainApp.finalWidth, FestivalMainApp.finalHeight).centerCrop().into(fbPostPicture);
                                    fbPostPicture.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            onPhotoClickListener.photoClikListener(imageUri);
                                        }
                                    });
                                } catch (JSONException e) {
                                    Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }

            /* handle the result */
                        }
                    }
            ).executeAsync();

        }
    }

    public interface OnPhotoClickListener {
        void photoClikListener(String imageuri);
    }
}
