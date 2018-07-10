package com.katsuro.alexey.minecafe.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.katsuro.alexey.minecafe.Category;
import com.katsuro.alexey.minecafe.Content;
import com.katsuro.alexey.minecafe.ContentAdapter;
import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.Detail;
import com.katsuro.alexey.minecafe.R;
import com.katsuro.alexey.minecafe.activities.CategoryActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 7/10/18.
 */

public class CategoryListFragment extends Fragment{

    private static final String TAG = CategoryListFragment.class.getSimpleName();
    private RecyclerView mCategoriesRecyclerView;
    private ContentAdapter mAdapter;
    private Category mCategory;

    public static CategoryListFragment newInstance() {
        CategoryListFragment fragment = new CategoryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(mCategory==null) {
            mCategory = ContentLab.get(getActivity()).getStartCategory();
        }

    }

    private void updateUI() {
        if(mAdapter == null){
            mAdapter = new ContentAdapter(R.layout.category_list_item,mCategory.getContents());
            mCategoriesRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setContentList(mCategory.getContents());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category_list,container,false);

        mCategoriesRecyclerView = view.findViewById(R.id.categories_recycler_view);
        mCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        updateUI();
        return view;
    }



//    private class CategoriesHolder extends RecyclerView.ViewHolder
//            implements View.OnClickListener{
//
//        private ImageButton mCategoryImageButton;
//        private TextView mTitleTextView;
//        private Category mCategory;
//        private Context mContext;
//
//        public CategoriesHolder(Context context, View itemView) {
//            super(itemView);
//            mContext = context;
//            mCategoryImageButton = itemView.findViewById(R.id.category_image_button);
//            mTitleTextView = itemView.findViewById(R.id.category_title);
//            mCategoryImageButton.setOnClickListener(this);
//        }
//
//        public void bindHolder(Category category){
//            mCategory = category;
//            mCategoryImageButton.setImageResource(mCategory.getDrawableId());
//            mTitleTextView.setText(mCategory.getTitle());
//        }
//
//        @Override
//        public void onClick(View v) {
//            Intent intent = CategoryActivity.newIntent(mContext,mCategory);
//            startActivity(intent);
//        }
//    }
//    private class CategoriesAdapter extends RecyclerView.Adapter<CategoriesHolder>{
//
//        private Context mContext;
//        private List<Category> mCategoryList;
//
//        public CategoriesAdapter(Context context, List<Category> categoryList) {
//            mContext = context;
//            mCategoryList = categoryList;
//        }
//
//        @Override
//        public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(mContext)
//                    .inflate(R.layout.category_list_item,parent,false);
//
//            return new CategoriesHolder(mContext,view);
//        }
//
//        @Override
//        public void onBindViewHolder(CategoriesHolder holder, int position) {
//            holder.bindHolder(mCategoryList.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mCategoryList.size();
//        }
//
//        public List<Category> getCategoryList() {
//            return mCategoryList;
//        }
//
//        public void setCategoryList(List<Category> categoryList) {
//            mCategoryList = categoryList;
//        }
//    }


}
