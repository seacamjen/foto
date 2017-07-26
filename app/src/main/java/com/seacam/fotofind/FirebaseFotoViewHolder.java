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
 * Created by jensensc on 7/25/17.
 */

public class FirebaseFotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseFotoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindFoto(Fotos fotos) {
        ImageView fotoTaken = (ImageView) mView.findViewById(R.id.foto_image);

        try {
            Bitmap imageBitmap = decodeFromFirebaseBase64(fotos.getImage());
            fotoTaken.setImageBitmap(imageBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

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
