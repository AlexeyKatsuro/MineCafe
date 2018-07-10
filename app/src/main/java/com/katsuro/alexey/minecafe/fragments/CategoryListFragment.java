package com.katsuro.alexey.minecafe.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.katsuro.alexey.minecafe.Category;
import com.katsuro.alexey.minecafe.R;
import com.katsuro.alexey.minecafe.activities.CategoryActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by alexey on 7/10/18.
 */

public class CategoryListFragment extends Fragment{

    private RecyclerView mCategoriesRecyclerView;
    private CategoriesAdapter mAdapter;
    private List<Category> mCategoryList;

    public static CategoryListFragment newInstance() {
        CategoryListFragment fragment = new CategoryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(mCategoryList == null){
            mCategoryList = getStartCategories();
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_categories,container,false);

        mCategoriesRecyclerView = view.findViewById(R.id.categories_recycler_view);
        mCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        updateUI();
        return view;
    }

    private void updateUI() {
        if(mAdapter == null){
            mAdapter = new CategoriesAdapter(getActivity(),mCategoryList);
            mCategoriesRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCategoryList(mCategoryList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CategoriesHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private ImageButton mCategoryImageButton;
        private TextView mTitleTextView;
        private Category mCategory;
        private Context mContext;

        public CategoriesHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mCategoryImageButton = itemView.findViewById(R.id.category_image_button);
            mTitleTextView = itemView.findViewById(R.id.category_title);
            mCategoryImageButton.setOnClickListener(this);
        }

        public void bindHolder(Category category){
            mCategory = category;
            mCategoryImageButton.setImageResource(mCategory.getDrawableId());
            mTitleTextView.setText(mCategory.getTitle());
        }

        @Override
        public void onClick(View v) {
            Intent intent = CategoryActivity.newIntent(mContext,mCategory);
            startActivity(intent);
        }
    }
    private class CategoriesAdapter extends RecyclerView.Adapter<CategoriesHolder>{

        private Context mContext;
        private List<Category> mCategoryList;

        public CategoriesAdapter(Context context, List<Category> categoryList) {
            mContext = context;
            mCategoryList = categoryList;
        }

        @Override
        public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.category_layout,parent,false);

            return new CategoriesHolder(mContext,view);
        }

        @Override
        public void onBindViewHolder(CategoriesHolder holder, int position) {
            holder.bindHolder(mCategoryList.get(position));
        }

        @Override
        public int getItemCount() {
            return mCategoryList.size();
        }

        public List<Category> getCategoryList() {
            return mCategoryList;
        }

        public void setCategoryList(List<Category> categoryList) {
            mCategoryList = categoryList;
        }
    }

    private List<Category> getStartCategories() {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category(getString(R.string.burgers), R.drawable.burger, null);
        Category category1 = new Category(getString(R.string.sandwiches), R.drawable.sandwich, null);
        Category category2 = new Category(getString(R.string.pizza), R.drawable.pizza, null);
        Category category3 = new Category(getString(R.string.salads), R.drawable.salad, null);
        Category category4 = new Category(getString(R.string.beverages), R.drawable.beverage, null);
        Category category5 = new Category(getString(R.string.dessert), R.drawable.dessert, null);

        categoryList.add(category);
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);
        return categoryList;
    }
}
