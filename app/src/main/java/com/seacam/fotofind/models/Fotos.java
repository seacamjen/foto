package com.seacam.fotofind.models;

public class Fotos {
    double latitude;
    double longitude;
    long time;
    String image;

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
}
