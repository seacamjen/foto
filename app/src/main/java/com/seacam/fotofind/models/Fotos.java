package com.seacam.fotofind.models;

public class Fotos {
    float latitude;
    float longitude;
    float compass;

    public Fotos() {}

    public Fotos(float latitude, float longitude, float compass) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.compass = compass;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getCompass() {
        return compass;
    }
}
