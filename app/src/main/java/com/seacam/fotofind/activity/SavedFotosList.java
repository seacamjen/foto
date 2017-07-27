package com.seacam.fotofind.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
        mFotosRef = FirebaseDatabase.getInstance().getReference().child("photos");

        mFotosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot foto : dataSnapshot.getChildren()) {
                    String fotos = foto.child("image").getValue().toString();
                    Log.d("Foto updated", "Foto: " + fotos);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_fotos_list);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        int numberOfColumns = 3;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_saved_fotos_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_map) {
            Intent intent = new Intent(SavedFotosList.this, MapActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_foto) {
            Intent intent = new Intent(SavedFotosList.this, CameraActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_exit) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SavedFotosList.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
