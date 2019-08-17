package com.pst.SuperShop.models;

public class DatosItem {

    private int precio;
    private String nombre;
    private String fotourl;
    private boolean isSelected = false;

    public DatosItem(int precio, String nombre, String fotourl) {
        this.precio = precio;
        this.nombre = nombre;
        this.fotourl = fotourl;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
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