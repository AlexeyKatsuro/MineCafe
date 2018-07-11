package com.katsuro.alexey.minecafe.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.fragments.CategoryFragment;
import com.katsuro.alexey.minecafe.fragments.CategoryListFragment;
import com.katsuro.alexey.minecafe.model.Category;

public class CategoryListActivity extends SingleFragmentActivity {

    public static final String EXTRA_CATEGORY = "extra_category";
    private static final String TAG = CategoryListActivity.class.getSimpleName();

    @Override
    public Fragment createFragment() {
        Log.d(TAG,"createFragment");
        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            String title = extras.getString(EXTRA_CATEGORY, null);
            Category category = (Category) ContentLab.get(this).getContent(title);
            return CategoryListFragment.newInstance(category);
        } else {
            return CategoryListFragment.newInstance();
        }
    }


    public static Intent newIntent(Context context,Category category){
        Log.d(TAG,"newIntent");
        Intent intent = new Intent(context,CategoryListActivity.class);
        intent.putExtra(EXTRA_CATEGORY,category.getTitle());

        return intent;
    }
}
