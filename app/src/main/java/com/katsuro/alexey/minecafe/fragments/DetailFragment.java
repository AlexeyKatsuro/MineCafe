package com.katsuro.alexey.minecafe.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.R;
import com.katsuro.alexey.minecafe.model.Detail;

/**
 * Created by alexey on 7/10/18.
 */

public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.getSimpleName();
    private static final String ARG_DETAIL = "arg_detail";

    private Detail mDetail;
    private ImageView mDetailImageView;
    private TextView mDescriptionTextView;

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

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.color.colorPrimaryTrans);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(mDetail.getTitle());

        mDetailImageView = view.findViewById(R.id.detail_image_view);
        mDescriptionTextView = view.findViewById(R.id.description_text_view);

        mDetailImageView.setImageResource(mDetail.getDrawableId());
        mDescriptionTextView.setText(mDetail.getDescription());
        return view;
    }
}
