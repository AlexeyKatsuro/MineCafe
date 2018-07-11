package com.katsuro.alexey.minecafe.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.katsuro.alexey.minecafe.ContentLab;
import com.katsuro.alexey.minecafe.model.Detail;
import com.katsuro.alexey.minecafe.fragments.DetailFragment;

public class DetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_DETAIL = "extra_category";

    @Override
    public Fragment createFragment() {
        Bundle extras = getIntent().getExtras();
        String title = extras.getString(EXTRA_DETAIL,null);
        Detail detail = (Detail) ContentLab.get(this).getContent(title);
        return DetailFragment.newInstance(detail);
    }

    public static Intent newIntent(Context context, Detail detail){
        Intent intent =  new Intent(context,DetailActivity.class);
        intent.putExtra(EXTRA_DETAIL,detail.getTitle());
        return intent;
    }
}
