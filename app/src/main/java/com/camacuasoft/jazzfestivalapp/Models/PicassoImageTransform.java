package com.camacuasoft.jazzfestivalapp.Models;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by nosotros on 24/06/2017.
 */

public class PicassoImageTransform implements Transformation {

    private int mSize;

    public PicassoImageTransform(int size){
        mSize = size;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        float scale;
        int newSize;
        Bitmap scaledBitmap;

        scale = (float) mSize / source.getWidth();
        newSize = Math.round(source.getHeight() * scale);
        scaledBitmap = Bitmap.createScaledBitmap(source, mSize, newSize, true);

        if(scaledBitmap != source) {
            source.recycle();
        }
        return scaledBitmap;
    }

    @Override
    public String key() {
        return "scaleRespectRatio"+mSize;
    }
}
