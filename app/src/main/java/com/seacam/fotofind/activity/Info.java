package com.seacam.fotofind.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import info.androidhive.locationapi.R;

public class Info extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
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
            Intent intent = new Intent(Info.this, MapActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_foto) {
            onLaunchCamera();
            return true;
        }
        if (id == R.id.action_exit) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Info.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
