package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class HealthFragment extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
    String url = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=21353109f25846cebd5f6cbbbe4b1a98";
    private static final int LoaderId = 1;
    private View rootview;
    private NewsListAdapter Newsadapter;
    private ListView listView;
    private static final String TAG = "HealthFragment";


    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_parent, container, false);
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        Log.e(TAG,"isconnected is"+isConnected);
        if (isConnected) {
            getLoaderManager().initLoader(LoaderId,null, this).forceLoad();
        } else {
            Toast.makeText(getActivity(), "No News found", Toast.LENGTH_SHORT).show();
        }
        listView = (ListView) rootview.findViewById(R.id.list1);


        return rootview;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void buildUI(List<News> newsList) {
//        ListView listView = (ListView) rootview.findViewById(R.id.list);
        Newsadapter = new NewsListAdapter(getActivity(), newsList);
        listView.setAdapter(Newsadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = Newsadapter.getItem(position);
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(news.getInfoLink()));
                startActivity(i);
            }
        });
    }


    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsListLoader(getActivity(), url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {
        if(news!=null && !news.isEmpty()){
            buildUI(news);
        }else {
            Toast.makeText(getActivity(), "No News found", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
Newsadapter.clear();
    }
}
