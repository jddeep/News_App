package com.example.android.newsapp;

public class News {
    private String title;
    private String description;
    private String InfoLink;
    private String thumbnail;
    private String publishDate;

    public News(String title, String description, String infoLink, String thumbnail, String publishDate) {

        this.title = title;
        this.description = description;
        InfoLink = infoLink;
        this.thumbnail = thumbnail;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInfoLink() {
        return InfoLink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
