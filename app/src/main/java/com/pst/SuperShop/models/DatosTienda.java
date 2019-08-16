package com.pst.SuperShop.models;

public class DatosTienda {
    private String urlPhoto;
    private String nombre;
    private float latitude;
    private float longitude;

    public DatosTienda(String urlPhoto, String nombre, float latitude, float longitude) {
        this.urlPhoto = urlPhoto;
        this.nombre = nombre;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
