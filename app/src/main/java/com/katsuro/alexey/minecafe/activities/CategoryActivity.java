package com.katsuro.alexey.minecafe.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.katsuro.alexey.minecafe.model.Category;
import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.fragments.CategoryFragment;

public class CategoryActivity extends SingleFragmentActivity {
    public static final String TAG = CategoryActivity.class.getSimpleName();

    public static final String EXTRA_CATEGORY = "extra_category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public Fragment createFragment() {
        Log.d(TAG,"createFragment");
        Bundle extras = getIntent().getExtras();
        String title = extras.getString(EXTRA_CATEGORY,null);
        Category category = (Category) ContentLab.get(this).getContent(title);
        return CategoryFragment.newInstance(category);
    }


    public static Intent newIntent(Context context,Category category){
        Log.d(TAG,"newIntent");
        Intent intent = new Intent(context,CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY,category.getTitle());

        return intent;
    }
}
