package com.katsuro.alexey.minecafe.holders;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.katsuro.alexey.minecafe.activities.CategoryListActivity;
import com.katsuro.alexey.minecafe.model.Category;
import com.katsuro.alexey.minecafe.model.Content;
import com.katsuro.alexey.minecafe.R;
import com.katsuro.alexey.minecafe.activities.CategoryActivity;
import com.katsuro.alexey.minecafe.model.Detail;

/**
 * Created by alexey on 7/10/18.
 */

public class CategoriesHolder extends ContentHolder {
    private static final String TAG = CategoriesHolder.class.getSimpleName();
    private ImageButton mCategoryImageButton;
    private TextView mTitleTextView;
    private Category mCategory;


    public CategoriesHolder(Context context, View itemView) {
        super(context, itemView);
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
    public Content getContent() {
        return mCategory;
    }


    @Override
    public void onClick(View v) {
        if(mCategory.getContents() == null || mCategory.getContents().size()<0){
            return;
        }

        Intent intent = null;
        if(mCategory.getContents().get(0) instanceof Detail) {
            intent = CategoryActivity.newIntent(getContext(), mCategory);
        }

        if(mCategory.getContents().get(0) instanceof Category) {
            intent = CategoryListActivity.newIntent(getContext(), mCategory);
        }
        getContext().startActivity(intent);
        mOnHolderItemClickListener.onHolderItemClick(this,v);
    }


}