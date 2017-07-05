package com.seacam.fotofind.models;

public class Fotos {
    float latitude;
    float longitude;
    float compass;
    String image;

    public Fotos() {}

    public Fotos(float latitude, float longitude, float compass, String image) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.compass = compass;
        this.image = image;
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
    public String getImage() {
        return image;
    }
}
