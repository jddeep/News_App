package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class NewsListLoader extends AsyncTaskLoader {
    private String mUrl;
private List<News> newsList;
    public NewsListLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        try {
            newsList = QueryUtils.fetchNewsData(mUrl);

        } catch (IOException e) {
            Log.e("LoaderActivity", "Error in loadinBackground");
        }
        return newsList;
    }
}
