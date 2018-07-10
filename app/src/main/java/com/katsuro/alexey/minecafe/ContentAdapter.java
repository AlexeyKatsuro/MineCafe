package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alexey on 7/10/18.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentHolder> {

    private List<Content> mContentList;
    private int mLayout;

    public ContentAdapter(int layout, List<Content> contentList) {
        mLayout = layout;
        mContentList = contentList;
    }

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(mLayout,parent,false);
        ContentHolder contentHolder = null;
        if(mContentList.get(0) instanceof Category){
           contentHolder = new CategoriesHolder(context,view);
        }

        if(mContentList.get(0) instanceof Detail){
            contentHolder = new DetailHolder(context,view);
        }
        return contentHolder;
    }

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
}
