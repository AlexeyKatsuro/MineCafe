package com.katsuro.alexey.minecafe.model;



public class Detail extends Content {

    private int mIconId;
    private String mDescription;

    public Detail(String title,String description, int drawableId, int iconId) {
        super(title,drawableId);
        mIconId = iconId;
        mDescription = description;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        mIconId = iconId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
