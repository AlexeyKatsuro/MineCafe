package com.katsuro.alexey.minecafe;

/**
 * Created by alexey on 7/10/18.
 */

public class Content  {

    private String mTitle;
    private int mDrawableId;

    public Content() {

    }

    public Content(String title, int drawableId) {
        mTitle = title;
        mDrawableId = drawableId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int drawableId) {
        mDrawableId = drawableId;
    }
}
