package com.pst.SuperShop.models;

public class ItemFromStore {

    private String itemPrice;
    private String itemDesc;
    private boolean isSelected = false;

    public ItemFromStore(String itemPrice, String itemDesc) {
        this.itemPrice = itemPrice;
        this.itemDesc = itemDesc;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}