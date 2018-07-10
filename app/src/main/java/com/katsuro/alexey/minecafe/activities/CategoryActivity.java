package com.katsuro.alexey.minecafe.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.katsuro.alexey.minecafe.Category;
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
        String jsonCategory = extras.getString(EXTRA_CATEGORY,null);
        Category category = new Gson().fromJson(jsonCategory,Category.class);
        return CategoryFragment.newInstance(category);
    }


    public static Intent newIntent(Context context,Category category){
        Log.d(TAG,"newIntent");
        Intent intent = new Intent(context,CategoryActivity.class);
        String jsonCategory = new Gson().toJson(category);
        intent.putExtra(EXTRA_CATEGORY,jsonCategory);

        return intent;
    }
}
