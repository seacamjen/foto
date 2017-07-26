package com.seacam.fotofind.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seacam.fotofind.FirebaseFotoViewHolder;
import com.seacam.fotofind.models.Fotos;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.androidhive.locationapi.R;

public class SavedFotosList extends AppCompatActivity {
    private DatabaseReference mFotosRef;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_fotos_list);
        ButterKnife.bind(this);

        mFotosRef = FirebaseDatabase.getInstance().getReference("photos").child("image");
        Log.i("Firebase", mFotosRef.toString());

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Fotos, FirebaseFotoViewHolder>(Fotos.class, R.layout.foto_list_item, FirebaseFotoViewHolder.class, mFotosRef) {
            @Override
            protected void populateViewHolder(FirebaseFotoViewHolder viewHolder, Fotos model, int position) {
                viewHolder.bindFoto(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
