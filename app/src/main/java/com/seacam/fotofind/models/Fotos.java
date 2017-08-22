package com.seacam.fotofind.models;

import org.parceler.Parcel;

@Parcel
public class Fotos {
    double latitude;
    double longitude;
    long time;
    String image;
    private String pushId;
    String index;

    public Fotos() {}

    public Fotos(double latitude, double longitude, long time, String image) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.image = image;
        this.index = "not specified";
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

    public String getIndex(){
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
