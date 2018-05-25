package com.camacuasoft.jazzfestivalapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.PicassoImageTransform;
import com.camacuasoft.jazzfestivalapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nosotros on 20/06/2017.
 */

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistRecyclerAdapter.ViewHolder>{

    private List<Artist> artists;
    private int layout;
    private OnFavBtnClickListener favBtnClickListener;
    private OnItemClickListener itemClickListener;

    private Context context;

    public ArtistRecyclerAdapter(List<Artist> artists, int layout, OnFavBtnClickListener clickListener, OnItemClickListener itemClickListener) {
        this.artists = artists;
        this.layout = layout;
        this.favBtnClickListener = clickListener;
        this.itemClickListener = itemClickListener;
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
        holder.Bind(artists.get(position), favBtnClickListener, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView artistItemImage;
        TextView artistItemName;
        ImageView artistItemFavBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            artistItemImage = (ImageView) itemView.findViewById(R.id.artist_list_image);
            artistItemName = (TextView) itemView.findViewById(R.id.artist_list_name);
            artistItemFavBtn = (ImageView) itemView.findViewById(R.id.artist_list_fav_btn);
        }

        public void Bind(final Artist artist, final OnFavBtnClickListener favBtnListener, final OnItemClickListener itemClickListener) {
            final BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), artist.getPhotoResInt(), opt);
            itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                    int imageWidth = opt.outWidth;
                    int imageHeight = opt.outHeight;
                    int finalHeight = Math.round((float)artistItemImage.getWidth()/imageWidth*imageHeight);
                    Picasso.with(context).load(artist.getPhotoRes()).resize(artistItemImage.getWidth(), finalHeight).centerCrop().into(artistItemImage);
                    return false;
                }
            });
            artistItemName.setText(artist.getName());
            if(artist.isFavorite()){
                artistItemFavBtn.setImageResource(R.drawable.ic_action_fav_yes);
            } else {
                artistItemFavBtn.setImageResource(R.drawable.ic_action_fav_no);
            }
            artistItemFavBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favBtnListener.onFavBtnClik(artist);
                    if(artist.isFavorite()) {
                        artistItemFavBtn.setImageResource(R.drawable.ic_action_fav_yes);
                    } else {
                        artistItemFavBtn.setImageResource(R.drawable.ic_action_fav_no);
                    }
                }
            });

            artistItemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemSelected(artist);
                }
            });

            artistItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemSelected(artist);
                }
            });

        }
    }

    public interface OnFavBtnClickListener {
        void onFavBtnClik(Artist artist);
    }

    public interface OnItemClickListener {
        void onItemSelected(Artist artist);
    }
}
