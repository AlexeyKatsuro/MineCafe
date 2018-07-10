package com.katsuro.alexey.minecafe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.katsuro.alexey.minecafe.Category;
import com.katsuro.alexey.minecafe.Content;
import com.katsuro.alexey.minecafe.ContentAdapter;
import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.Detail;
import com.katsuro.alexey.minecafe.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private final static String AGR_CATEGORY = "arg_category";
    private static final String TAG = CategoryFragment.class.getSimpleName();

    private RecyclerView mDetailRecyclerView;
    private ContentAdapter mAdapter;

    private Category mCategory;


    public static CategoryFragment newInstance(Category category) {

        Bundle args = new Bundle();
        //String jsonCategory = new Gson().toJson(category);
        args.putString(AGR_CATEGORY,category.getTitle());
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void setTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    private void updateUI() {
        if(mAdapter == null){
            mAdapter = new ContentAdapter(R.layout.detail_list_item,mCategory.getContents());
            mDetailRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setContentList(mCategory.getContents());
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        Bundle args  = getArguments();
        String title = args.getString(AGR_CATEGORY);
       // String jsonCategory = args.getString(AGR_CATEGORY,null);
        mCategory = (Category) ContentLab.get(getActivity()).getContent(title);
        //mCategory = new Gson().fromJson(jsonCategory,Category.class);
        setTitle(mCategory.getTitle());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category,container,false);
        mDetailRecyclerView = view.findViewById(R.id.detail_recycler_view);
        mDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();
        return view;
    }



//    private class DetailHolder extends RecyclerView.ViewHolder
//            implements View.OnClickListener{
//
//        private ImageView mDetailIcon;
//        private TextView mTitleTextView;
//        private Detail mDetail;
//        private Context mContext;
//
//        public DetailHolder(Context context, View itemView) {
//            super(itemView);
//            mContext = context;
//            mDetailIcon = itemView.findViewById(R.id.icon_image_view);
//            mTitleTextView = itemView.findViewById(R.id.title_text_view);
//            itemView.setOnClickListener(this);
//        }
//
//        public void bindHolder(Detail detail){
//            mDetail = detail;
//            mDetailIcon.setImageResource(mDetail.getIconId());
//            mTitleTextView.setText(mDetail.getTitle());
//        }
//
//        @Override
//        public void onClick(View v) {
////            Intent intent = CategoryActivity.newIntent(mContext,mCategory);
////            startActivity(intent);
//        }
//    }
//    private class DetailAdapter extends RecyclerView.Adapter<DetailHolder>{
//
//        private Context mContext;
//        private List<Detail> mDetailList;
//
//
//        public DetailAdapter(Context context, List<Detail> detailList) {
//            mContext = context;
//            mDetailList = detailList;
//        }
//
//        @Override
//        public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(mContext)
//                    .inflate(R.layout.detail_list_item,parent,false);
//
//            return new DetailHolder(mContext,view);
//        }
//
//        @Override
//        public void onBindViewHolder(DetailHolder holder, int position) {
//            holder.bindHolder(mDetailList.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mDetailList.size();
//        }
//
//        public List<Detail> getDetailList() {
//            return mDetailList;
//        }
//
//        public void setDetailList(List<Detail> detailList) {
//            mDetailList = detailList;
//        }
//    }

}
