package com.camacuasoft.jazzfestivalapp.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.DateAndTime;
import com.camacuasoft.jazzfestivalapp.Models.News;
import com.camacuasoft.jazzfestivalapp.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

/**
 * Created by nosotros on 20/06/2017.
 */

public class NewsReciclerAdapter extends RecyclerView.Adapter<NewsReciclerAdapter.ViewHolder> {

    private List<News> newsList;
    private int layout;
    private OnMoreButtonClickListener moreButtonPressed;

    private Context context;

    public NewsReciclerAdapter(List<News> newsList, int layout, OnMoreButtonClickListener buttonClickListener) {
        this.newsList = newsList;
        this.layout = layout;
        this.moreButtonPressed = buttonClickListener;
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
        holder.Bind(newsList.get(position), moreButtonPressed);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
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

        public void Bind(final News news, final OnMoreButtonClickListener listener){
            Calendar newsDateCal = Calendar.getInstance();
            newsDateCal.setTime(news.getDate());

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
            Picasso.with(context).load(news.getArtist().getPhotoRes()).resize(FestivalMainApp.finalWidth, FestivalMainApp.finalHeight).centerCrop().into(newsImage);


            newsDate.setText(DateAndTime.GetMonth(newsDateCal.get(Calendar.MONTH)-1) + " " +
             newsDateCal.get(Calendar.DAY_OF_MONTH) + ", " + newsDateCal.get(Calendar.YEAR));
            newsTitle.setText(news.getTitle());
            newsInfo.setText(news.getInfo());

            newsMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMoreButtonClick(news);
                }
            });



        }
    }

    public interface OnMoreButtonClickListener {
        void onMoreButtonClick(News news);
    }
}
