package com.katsuro.alexey.minecafe.holders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.katsuro.alexey.minecafe.model.Content;
import com.katsuro.alexey.minecafe.model.Detail;
import com.katsuro.alexey.minecafe.R;
import com.katsuro.alexey.minecafe.activities.DetailActivity;

/**
 * Created by alexey on 7/10/18.
 */

public class DetailHolder extends ContentHolder {

    private ImageView mDetailIcon;
    private TextView mTitleTextView;
    private Detail mDetail;

    public DetailHolder(Context context, View itemView) {
        super(context,itemView);
        mDetailIcon = itemView.findViewById(R.id.icon_image_view);
        mTitleTextView = itemView.findViewById(R.id.title_text_view);
        itemView.setOnClickListener(this);
    }

    @Override
    public void bindHolder(Content content) {

        mDetail = (Detail) content;
        mDetailIcon.setImageResource(mDetail.getIconId());
        mTitleTextView.setText(mDetail.getTitle());
    }

    @Override
    public Content getContent() {
        return mDetail;
    }

    @Override
    public void onClick(View v) {
            Intent intent = DetailActivity.newIntent(getContext(),mDetail);
            getContext().startActivity(intent);
            mOnHolderItemClickListener.onHolderItemClick(this,v);
    }
}