package com.katsuro.alexey.minecafe;

import java.util.List;

/**
 * Created by alexey on 7/10/18.
 */

public class Category  extends Content {

    private List<Content> mContents;

    public Category(String title, int drawableId, List<Content> contents) {
        super(title,drawableId);
        mContents = contents;
    }

    public List<Content> getContents() {
        return mContents;
    }

    public void setContents(List<Content> contents) {
        mContents = contents;
    }
}
