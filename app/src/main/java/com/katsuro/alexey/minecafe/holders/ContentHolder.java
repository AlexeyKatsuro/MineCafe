package com.katsuro.alexey.minecafe.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.katsuro.alexey.minecafe.ContentAdapter;
import com.katsuro.alexey.minecafe.model.Content;

/**
 * Created by alexey on 7/10/18.
 */

public abstract class ContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;
    protected ContentAdapter.OnHolderItemClickListener mOnHolderItemClickListener;


    public ContentHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
    }

    public abstract void bindHolder(Content content);


    public Context getContext() {
        return mContext;
    }

    public abstract Content getContent();

    public void setOnHolderItemClickListener(ContentAdapter.OnHolderItemClickListener onHolderItemClickListener) {
        mOnHolderItemClickListener = onHolderItemClickListener;
    }
}
