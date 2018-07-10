package com.katsuro.alexey.minecafe.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.katsuro.alexey.minecafe.Category;


public class CategoryFragment extends Fragment {

    private final static String AGR_CATEGORY = "arg_category";

    public static final String TAG = CategoryFragment.class.getSimpleName();
    private Category mCategory;

    public static CategoryFragment newInstance(Category category) {

        Bundle args = new Bundle();
        String jsonCategory = new Gson().toJson(category);
        args.putString(AGR_CATEGORY,jsonCategory);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void setTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        Bundle args  = getArguments();
        String jsonCategory = args.getString(AGR_CATEGORY,null);
        mCategory = new Gson().fromJson(jsonCategory,Category.class);
        setTitle(mCategory.getTitle());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }
}
