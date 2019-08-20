package com.pst.SuperShop.models;

import java.io.Serializable;

public class DatosItem implements Serializable {

    private double precio;
    private String nombre;
    private String fotourl;
    private boolean isSelected = false;

    public DatosItem() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFotourl() {
        return fotourl;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isSelected() {
        return isSelected;
    }
}