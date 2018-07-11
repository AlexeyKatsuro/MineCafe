package com.katsuro.alexey.minecafe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.katsuro.alexey.minecafe.AdBlocker;
import com.katsuro.alexey.minecafe.holders.ContentHolder;
import com.katsuro.alexey.minecafe.holders.DetailHolder;
import com.katsuro.alexey.minecafe.model.Category;
import com.katsuro.alexey.minecafe.ContentAdapter;
import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.R;


public class CategoryFragment extends Fragment implements RewardedVideoAdListener {

    private final static String ARG_CATEGORY = "arg_category";
    private static final String TAG = CategoryFragment.class.getSimpleName();

    private RewardedVideoAd mRewardedVideoAd;
    private AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();

    private RecyclerView mDetailRecyclerView;
    private ContentAdapter mAdapter;

    private Category mCategory;

    public static CategoryFragment newInstance(Category category) {

        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY,category.getTitle());
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void setTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    private void updateUI() {
        if(mAdapter == null){
            mAdapter = new ContentAdapter(R.layout.detail_list_item,mCategory.getContents()){

                @Override
                public ContentHolder createContentHolder(Context context, View view) {
                    return new DetailHolder(context,view);
                }
            };
            mAdapter.setOnHolderItemClickListener(new ContentAdapter.OnHolderItemClickListener() {
                @Override
                public void onHolderItemClick(ContentHolder holder, View view) {
                    if (mRewardedVideoAd.isLoaded() &&
                            AdBlocker.get(getActivity()).isRewardedAdAvailable()) {
                        mRewardedVideoAd.show();
                    }
                }
            });
            mDetailRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setContentList(mCategory.getContents());
            mAdapter.notifyDataSetChanged();
        }
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                adRequest);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        Bundle args  = getArguments();
        String title = args.getString(ARG_CATEGORY);
        mCategory = (Category) ContentLab.get(getActivity()).getContent(title);



        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category,container,false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.colorPrimaryTrans);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(mCategory.getTitle());

        mDetailRecyclerView = view.findViewById(R.id.detail_recycler_view);
        mDetailRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();
        return view;
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

        AdBlocker.get(getActivity()).blockRewardedAdOnTime(30*1000);
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }
}
