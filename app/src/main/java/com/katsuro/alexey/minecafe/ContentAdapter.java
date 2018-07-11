package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katsuro.alexey.minecafe.holders.CategoriesHolder;
import com.katsuro.alexey.minecafe.holders.ContentHolder;
import com.katsuro.alexey.minecafe.holders.DetailHolder;
import com.katsuro.alexey.minecafe.model.Category;
import com.katsuro.alexey.minecafe.model.Content;
import com.katsuro.alexey.minecafe.model.Detail;

import java.util.List;

/**
 * Created by alexey on 7/10/18.
 */

public abstract class  ContentAdapter extends RecyclerView.Adapter<ContentHolder> {

    private List<Content> mContentList;
    private int mLayout;
    private OnHolderItemClickListener mOnHolderItemClickListener;

    public ContentAdapter(int layout, List<Content> contentList) {
        mLayout = layout;
        mContentList = contentList;
    }

    public interface OnHolderItemClickListener {
        void onHolderItemClick(ContentHolder holder, View view);
    }

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(mLayout,parent,false);
        ContentHolder contentHolder = createContentHolder(context,view);
        contentHolder.setOnHolderItemClickListener(mOnHolderItemClickListener);

        return contentHolder;
    }

    public abstract ContentHolder createContentHolder(Context context, View view);

    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {
        holder.bindHolder(mContentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    public List<Content> getContentList() {
        return mContentList;
    }

    public void setContentList(List<Content> contentList) {
        mContentList = contentList;
    }

    public void setOnHolderItemClickListener(OnHolderItemClickListener onHolderItemClickListener) {
        mOnHolderItemClickListener = onHolderItemClickListener;
    }
}
