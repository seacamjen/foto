package com.seacam.fotofind.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seacam.fotofind.models.Fotos;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.androidhive.locationapi.R;

public class SaveFoto extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private DatabaseReference mActiveRef;

    private double latitude;
    private double longitude;
    private long time;
    private String foto;
    private boolean mLocationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final String TAG = SaveFoto.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private Location mLastLocation;

    private GoogleApiClient mGoogleApiClient;
    @Bind(R.id.saveFoto) TextView mSaveFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActiveRef = FirebaseDatabase
                .getInstance()
                .getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_foto);
        ButterKnife.bind(this);

        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        Intent intent = getIntent();
        foto = intent.getStringExtra("foto");

        mSaveFoto.setOnClickListener(this);
    }

    //begin location


    private void displayLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (mLastLocation != null) {
                latitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
                time = mLastLocation.getTime();
            } else {
                //log something?
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    protected boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();

            } else {
                Toast.makeText(getApplicationContext(), "This device is not supported.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    //end of location

    @Override
    public void onClick(View v) {
        if (v == mSaveFoto) {
            double lat = latitude;
            double longi = longitude;
            long currentTime = time;
            String pics = foto;

            Fotos fotos = new Fotos(lat, longi, currentTime, pics);

            saveFotoToFirebase(fotos);
        }
    }

    public void saveFotoToFirebase(Fotos fotos) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference photoRef = FirebaseDatabase.getInstance().getReference("photos").child(uid);

        DatabaseReference pushRef = photoRef.push();
        String pushId = pushRef.getKey();
        fotos.setPushId(pushId);
        pushRef.setValue(fotos);
        Toast.makeText(getApplicationContext(), "Foto is saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SaveFoto.this, MapActivity.class);
        startActivity(intent);
    }
}
