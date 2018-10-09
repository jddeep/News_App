package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String x;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SportsFragment();
        } else if (position == 1) {
            return new TechnologyFragment();
        } else if (position == 2) {
            return new PoliticsFragment();
        } else if (position == 3) {
            return new HealthFragment();
        } else {
            return new EntertainmentFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_sports);
        } else if (position == 1) {
            return mContext.getString(R.string.category_technology);
        } else if (position == 2) {
            return mContext.getString(R.string.category_politics);
        } else if (position == 3) {
            return mContext.getString(R.string.category_health);
        } else {
            return mContext.getString(R.string.category_entertainment);
        }

    }
}
