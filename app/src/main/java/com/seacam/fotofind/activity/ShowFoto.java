package com.seacam.fotofind.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seacam.fotofind.FirebaseFotoViewHolder;
import com.seacam.fotofind.models.Fotos;
import com.seacam.fotofind.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.androidhive.locationapi.R;

/**
 * Created by jensensc on 8/1/17.
 */

public class ShowFoto extends AppCompatActivity {
    private DatabaseReference mFotosRef;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_fotos_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Double longitude = intent.getDoubleExtra("longitude", 0);
        Double latitude = intent.getDoubleExtra("latitude", 0);

        String longitudes = longitude.toString();

        mFotosRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_DATABASE_PHOTOS).child(uid).child(longitudes);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        int numberOfColumns = 1;
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Fotos, FirebaseFotoViewHolder>(Fotos.class, R.layout.foto_list_item, FirebaseFotoViewHolder.class, mFotosRef) {
            @Override
            protected void populateViewHolder(FirebaseFotoViewHolder viewHolder, Fotos model, int position) {
                viewHolder.bindFoto(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
