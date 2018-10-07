package com.example.android.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class NewsListAdapter extends ArrayAdapter<News> {
    public NewsListAdapter(@NonNull Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if(listitemView == null){
            listitemView= LayoutInflater.from(getContext()).inflate(R.layout.news_list,parent,false);

        }
        News currentNews = getItem(position);
        TextView title = (TextView) listitemView.findViewById(R.id.title);
        title.setText(currentNews.getTitle());
        TextView description = (TextView) listitemView.findViewById(R.id.description);
        description.setText(currentNews.getDescription());
        TextView publishDate = (TextView) listitemView.findViewById(R.id.publishdate);
        publishDate.setText(currentNews.getPublishDate());
        ImageView thumbnail = (ImageView) listitemView.findViewById(R.id.thumbnail);

        DownloadImageTask downloadImageTask = new DownloadImageTask(thumbnail);
        downloadImageTask.execute(currentNews.getThumbnail());
    return listitemView;
    }

    public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {

        ImageView thumbnail;

        public DownloadImageTask(ImageView bmImage) {
            thumbnail = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            thumbnail.setImageBitmap(result);


        }
    }

}
