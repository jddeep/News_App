package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private QueryUtils() {

    }

    public static List<News> extractNewsJSON(String jsonResp) throws IOException {
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }
        List<News> books = new ArrayList<>();
        String author = "";
        try {
            JSONObject response = new JSONObject(jsonResp);
            JSONArray articles = response.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject currentNews = articles.getJSONObject(i);

//                JSONObject volumeInfo = currentNews.getJSONObject("volumeInfo");
                String title = currentNews.getString("title");
//                JSONArray authors = currentNews.getJSONArray("authors");

//                for (int j = 0; j < authors.length(); j++) {
//                    author+=authors.getString(j);
//                    if(j!=authors.length()-1){
//                        author+=", ";
//                    }
//                }
                String description = currentNews.getString("description");
                String infoLink = currentNews.getString("url");
                String thumbnail =currentNews.getString("urlToImage");
                String publisheDate = currentNews.getString("publishedAt");
                publisheDate =  publisheDate.replace('T',' ');

                if(!description.equals("null") || !thumbnail.equals("null")) {
                    News news = new News(title, description, infoLink, thumbnail, publisheDate);

                    books.add(news);
                }else {
                    continue;
                }
            }
        } catch (JSONException e) {
            Log.e("Utils", "Error in extraction");
        }
        return books;
    }

    public static List<News> fetchNewsData(String requestUrl) throws IOException {
        // Create URL object
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Some fragment", "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<News> books = extractNewsJSON(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    private static URL createUrl(String Url) {
        URL url = null;
        try {
            url = new URL(Url);

        } catch (MalformedURLException e) {
            Log.e("Some Fragment ", "Error in creating url");
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonresponse = "";
        if (url == null) {
            return jsonresponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonresponse = readfromStream(inputStream);
            } else {
                Log.e("Utils activity", "Error in HttpRequest");
            }
        } catch (IOException e) {
            Log.e("Utils Act", "Error in parsing");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonresponse;
    }

    public static String readfromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}
