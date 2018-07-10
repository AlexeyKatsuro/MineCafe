package com.katsuro.alexey.minecafe.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.Detail;

/**
 * Created by alexey on 7/10/18.
 */

public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.getSimpleName();
    private static final String ARG_DETAIL = "arg_detail";

    private Detail mDetail;

    public static DetailFragment newInstance(Detail detail) {
        Bundle arg = new Bundle();
        arg.putString(ARG_DETAIL, detail.getTitle());
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    private void setTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        Bundle args = getArguments();
        String title = args.getString(ARG_DETAIL);
        mDetail = (Detail) ContentLab.get(getActivity()).getContent(title);
        setTitle(mDetail.getTitle());
    }
}
