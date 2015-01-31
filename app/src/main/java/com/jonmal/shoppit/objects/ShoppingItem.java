package com.jonmal.shoppit.objects;

public class ShoppingItem {

    //==========================================================
    //                  Fields
    //==========================================================

    private String mTitle;
    private String mDescritption;

    //==========================================================
    //                  Constructors
    //==========================================================

    public ShoppingItem() {
    }

    public ShoppingItem(String title, String description) {

        mTitle = title;
        mDescritption = description;
    }

    //==========================================================
    //                  Setters / Getters
    //==========================================================


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescritption() {
        return mDescritption;
    }

    public void semDescritption(String descritption) {
        this.mDescritption = descritption;
    }
}
