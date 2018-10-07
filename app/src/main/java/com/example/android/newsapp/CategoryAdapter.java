package com.example.android.newsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new SportsFragment();
        }else {
            return new SportsFragment();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}
