package com.seacam.fotofind.models;

import org.parceler.Parcel;

@Parcel
public class Fotos {
    double latitude;
    double longitude;
    long time;
    String image;
    private String pushId;

    public Fotos() {}

    public Fotos(double latitude, double longitude, long time, String image) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
