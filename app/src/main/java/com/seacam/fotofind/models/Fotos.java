package com.seacam.fotofind.models;

public class Fotos {
    String latitude;
    String longitude;
    String time;
    String image;

    public Fotos() {}

    public Fotos(String latitude, String longitude, String time, String image) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }
}
