package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context context;
    private String x;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new SportsFragment();
        }else if(position == 1){
            return new TechnologyFragment();
        }else if(position == 2){
            return new PoliticsFragment();
        }else {
            return new HealthFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return context.getString(R.string.category_sports);
        }else if(position == 1){
            return context.getString(R.string.category_technology);
        }else if(position == 2){
            return context.getString(R.string.category_politics);
        }else {
            return context.getString(R.string.category_health);
        }

    }
}
