package com.seacam.fotofind;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.seacam.fotofind.models.Fotos;

import java.io.IOException;

import info.androidhive.locationapi.R;

/**
 * Created by jensensc on 8/22/17.
 */

public class FirebaseFotoFoundViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;

    public ImageView mFotosImageView;

    public FirebaseFotoFoundViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindFoto(Fotos fotos) {
        mFotosImageView = (ImageView) mView.findViewById(R.id.foto_found_image);

        try {
            Bitmap imageBitmap = decodeFromFirebaseBase64(fotos.getImage());
            mFotosImageView.setImageBitmap(imageBitmap);
            //flip image only for computer emulator
//            mFotosImageView.setRotation(90);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        try {
            byte[] decodedByteArray = Base64.decode(image, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return (Bitmap) null;
    }
}
