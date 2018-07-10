package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alexey on 7/10/18.
 */

public abstract class ContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;
    private Content mContent;

    public ContentHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
    }

    public abstract void bindHolder(Content content);


    public Context getContext() {
        return mContext;
    }
}
