package com.seacam.fotofind;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.seacam.fotofind.models.Fotos;
import com.squareup.picasso.Picasso;

import info.androidhive.locationapi.R;

/**
 * Created by jensensc on 7/25/17.
 */

public class FirebaseFotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseFotoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindFoto(Fotos fotos) {
        ImageView fotoTaken = (ImageView) mView.findViewById(R.id.fotoImage);

        Picasso.with(mContext)
                .load(fotos.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(fotoTaken);
    }

    @Override
    public void onClick(View v) {

    }
}
