package com.katsuro.alexey.minecafe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.katsuro.alexey.minecafe.AdBlocker;
import com.katsuro.alexey.minecafe.holders.CategoriesHolder;
import com.katsuro.alexey.minecafe.holders.ContentHolder;
import com.katsuro.alexey.minecafe.model.Category;
import com.katsuro.alexey.minecafe.ContentAdapter;
import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.R;

/**
 * Created by alexey on 7/10/18.
 */

public class CategoryListFragment extends Fragment{

    private static final String TAG = CategoryListFragment.class.getSimpleName();
    private static final String ARG_CATEGORY = "arg_category";

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private  AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();

    private RecyclerView mCategoriesRecyclerView;
    private ContentAdapter mAdapter;


    private Category mCategory;

    private String APP_ID = "ca-app-pub-3185275635528943~9700824884";
    private String TRAINING_ID = "ca-app-pub-3940256099942544~3347511713";


    public static CategoryListFragment newInstance(Category category) {
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY,category.getTitle());
        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CategoryListFragment newInstance() {
        CategoryListFragment fragment = new CategoryListFragment();
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        MobileAds.initialize(getActivity(), TRAINING_ID);
        Bundle arg = getArguments();

        if(arg!=null) {
            String title = arg.getString(ARG_CATEGORY);
            mCategory = (Category) ContentLab.get(getActivity()).getContent(title);
        } else {
            mCategory = ContentLab.get(getActivity()).getStartCategory();
        }

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                AdBlocker.get(getActivity()).blockInterstitialAdOnTime(30*1000);
                mInterstitialAd.loadAd(adRequest);
            }
        });
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category_list,container,false);

        mCategoriesRecyclerView = view.findViewById(R.id.categories_recycler_view);
        mCategoriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        mAdView = view.findViewById(R.id.adView);

        mAdView.loadAd(adRequest);


        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.colorPrimaryTrans);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(mCategory.getTitle());

        updateUI();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        AdBlocker.get(getActivity()).cancelBlocks();
    }

    private void updateUI() {
        if(mAdapter == null){
            mAdapter = new ContentAdapter(R.layout.category_list_item, mCategory.getContents()) {
                @Override
                public ContentHolder createContentHolder(Context context, View view) {
                    return new CategoriesHolder(context,view);
                }
            };
            mAdapter.setOnHolderItemClickListener(new ContentAdapter.OnHolderItemClickListener() {
                @Override
                public void onHolderItemClick(ContentHolder holder, View view) {
                    if(mInterstitialAd.isLoaded() &&
                            AdBlocker.get(getActivity()).isInterstitialAdAvailable()) {
                        mInterstitialAd.show();
                    }
                }
            });

            mCategoriesRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setContentList(mCategory.getContents());
            mAdapter.notifyDataSetChanged();
        }
    }



}
