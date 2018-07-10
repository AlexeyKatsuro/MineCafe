package com.katsuro.alexey.minecafe;



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

    @Override
    public String draw() {
        return "Detail";
    }
}
