package com.katsuro.alexey.minecafe.activities;

import android.support.v4.app.Fragment;

import com.katsuro.alexey.minecafe.fragments.CategoryListFragment;

public class CategoryListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return CategoryListFragment.newInstance();
    }
}
