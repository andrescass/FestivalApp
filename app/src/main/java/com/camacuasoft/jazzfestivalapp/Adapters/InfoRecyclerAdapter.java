package com.camacuasoft.jazzfestivalapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.Models.Info;
import com.camacuasoft.jazzfestivalapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andres on 09/07/17.
 */

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.ViewHolder> {

    private List<Info> infoList;
    private int layout;

    private Context context;

    public InfoRecyclerAdapter(List<Info> infoList, int layout) {
        this.infoList = infoList;
        this.layout = layout;
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
        holder.Bind(infoList.get(position));
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsDate;
        TextView newsTitle;
        TextView newsInfo;
        Button newsMoreButton;

        public ViewHolder(View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.news_item_image);
            newsDate = (TextView) itemView.findViewById(R.id.news_item_date);
            newsTitle = (TextView) itemView.findViewById(R.id.news_item_title);
            newsInfo = (TextView) itemView.findViewById(R.id.news_item_info);
            newsMoreButton = (Button) itemView.findViewById(R.id.news_item_more_btn);
        }

        public void Bind(final Info info){

//            final BitmapFactory.Options opt = new BitmapFactory.Options();
//            opt.inJustDecodeBounds = true;
//            BitmapFactory.decodeResource(context.getResources(), news.getArtist().getPhotoRes(), opt);
//            itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                @Override
//                public boolean onPreDraw() {
//                    itemView.getViewTreeObserver().removeOnPreDrawListener(this);
//                    int imageWidth = opt.outWidth;
//                    int imageHeight = opt.outHeight;
//                    int finalHeight = Math.round((float)newsImage.getWidth()/imageWidth*imageHeight);
//                    Picasso.with(context).load(news.getArtist().getPhotoRes()).resize(newsImage.getWidth(), finalHeight).centerCrop().into(newsImage);
//                    return false;
//                }
//            });
            if(info.getImageUri() != null && info.getImageUri().length() > 2)
                Picasso.with(context).load(info.getImageUri()).resize(FestivalMainApp.finalWidth, FestivalMainApp.finalHeight).centerCrop().into(newsImage);


            newsDate.setVisibility(View.GONE);
            newsTitle.setText(info.getTitle());
            newsInfo.setText(info.getInfo());

            newsMoreButton.setVisibility(View.GONE);
        }
    }
}
