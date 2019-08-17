package com.pst.SuperShop.models;

public class DatosItem {

    private int precio;
    private String nombre;
    private String fotourl;
    //private String
    private boolean isSelected = false;


    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    public DatosItem(int precio, String nombre, String fotourl, boolean isSelected) {
        this.precio = precio;
        this.nombre = nombre;
        this.fotourl = fotourl;
        this.isSelected = isSelected;
    }

    //private String
    //private boolean isSelected = false;

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}