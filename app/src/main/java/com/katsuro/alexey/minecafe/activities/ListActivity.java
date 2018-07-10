package com.katsuro.alexey.minecafe.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.katsuro.alexey.minecafe.fragments.ListFragment;

public class ListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return ListFragment.newInstance();
    }
}
