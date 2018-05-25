package com.camacuasoft.jazzfestivalapp.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Show;
import com.camacuasoft.jazzfestivalapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by nosotros on 24/06/2017.
 */

public class CalInsideAdapter extends RecyclerView.Adapter<CalInsideAdapter.ViewHolder> {

    private List<Show> showList;
    private int layout;
    private OnFavBtnClickListener favBtnClickListener;
    private OnArtistClickListener artistClickListener;

    private Context context;

    public CalInsideAdapter(List<Show> showList, int layout, OnFavBtnClickListener favBtnClickListener,
                            OnArtistClickListener artistClickListener){
        this.showList = showList;
        this.layout = layout;
        this.favBtnClickListener = favBtnClickListener;
        this.artistClickListener = artistClickListener;
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
        holder.Bind(showList.get(position), favBtnClickListener, artistClickListener);
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView artistPhoto;
        TextView artistName;
        TextView artistOrigin;
        TextView showHour;
        ImageView favBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            artistPhoto = (ImageView) itemView.findViewById(R.id.cal_item_photo);
            artistName = (TextView) itemView.findViewById(R.id.cal_item_name);
            artistOrigin = (TextView) itemView.findViewById(R.id.cal_item_origin);
            showHour = (TextView) itemView.findViewById(R.id.cal_item_hour);
            favBtn = (ImageView) itemView.findViewById(R.id.cal_item_fav_btn);
        }

        public void Bind(final Show show, final OnFavBtnClickListener onFavBtnClickListener,
                         final OnArtistClickListener onArtistClickListener){
            final BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), show.getArtist().getPhotoResInt(), opt);
            itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                    int imageWidth = opt.outWidth;
                    int imageHeight = opt.outHeight;
                    int finalHeight = Math.round((float)artistPhoto.getWidth()/imageWidth*imageHeight);
                    Picasso.with(context).load(show.getArtist().getPhotoRes()).resize(artistPhoto.getWidth(), finalHeight).centerCrop().into(artistPhoto);
                    return false;
                }
            });

            artistName.setText(show.getArtist().getName());
            artistOrigin.setText(show.getArtist().getOrigin());
            final SimpleDateFormat calTabFormat = new SimpleDateFormat("HH:mm", Locale.US);
            showHour.setText(calTabFormat.format(show.getDate()));

            if(show.getArtist().isFavorite()){
                favBtn.setImageResource(R.drawable.ic_action_fav_yes);
            } else {
                favBtn.setImageResource(R.drawable.ic_action_fav_no);
            }

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFavBtnClickListener.onFavBtnClik(show.getArtist());
                    if(show.getArtist().isFavorite()) {
                        favBtn.setImageResource(R.drawable.ic_action_fav_yes);
                    } else {
                        favBtn.setImageResource(R.drawable.ic_action_fav_no);
                    }
                }
            });

            artistName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onArtistClickListener.onArtistClick(show.getArtist());
                }
            });

            artistPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onArtistClickListener.onArtistClick(show.getArtist());
                }
            });

        }
    }

    public interface OnFavBtnClickListener {
        void onFavBtnClik(Artist artist);
    }

    public interface OnArtistClickListener{
        void onArtistClick(Artist artist);
    }
}
