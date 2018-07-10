package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.katsuro.alexey.minecafe.activities.CategoryActivity;

/**
 * Created by alexey on 7/10/18.
 */

public class CategoriesHolder extends ContentHolder {

    private ImageButton mCategoryImageButton;
    private TextView mTitleTextView;
    private Category mCategory;

    public CategoriesHolder(Context context, View itemView) {
        super(context,itemView);
        mCategoryImageButton = itemView.findViewById(R.id.category_image_button);
        mTitleTextView = itemView.findViewById(R.id.category_title);
        mCategoryImageButton.setOnClickListener(this);
    }

    @Override
    public void bindHolder(Content content) {
        mCategory = (Category) content;
        mCategoryImageButton.setImageResource(mCategory.getDrawableId());
        mTitleTextView.setText(mCategory.getTitle());
    }


    @Override
    public void onClick(View v) {
        if(mCategory.getContents() == null || mCategory.getContents().size()<0){
            return;
        }

        Intent intent = CategoryActivity.newIntent(getContext(),mCategory);
        getContext().startActivity(intent);
    }
}