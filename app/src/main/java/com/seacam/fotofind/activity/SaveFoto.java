package com.seacam.fotofind.activity;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seacam.fotofind.models.Fotos;

import info.androidhive.locationapi.R;

public class SaveFoto extends AppCompatActivity {
    private DatabaseReference mActiveRef;

    private GestureDetectorCompat mDetector;

    private String latitude;
    private String longitude;
    private String time;
    private String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActiveRef = FirebaseDatabase
                .getInstance()
                .getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_foto);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        time = intent.getStringExtra("time");
        foto = intent.getStringExtra("foto");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            String lat = latitude;
            String longi = longitude;
            String currentTime = time;
            String pics = foto;

            Fotos fotos = new Fotos(lat, longi, currentTime, pics);
            saveFotoToFirebase(fotos);
            return true;
        }
    }

    public void saveFotoToFirebase(Fotos fotos) {
        mActiveRef.child("photos").push().setValue(fotos);
        Toast.makeText(getApplicationContext(), "Item is saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SaveFoto.this, MainActivity.class);
        startActivity(intent);
    }
}
