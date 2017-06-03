package com.lynkmieu.guujoup.model;

import java.io.Serializable;

/**
 * Created by LynkMieu on 6/1/2017.
 */

public class Article implements Serializable{
    private String title;
    private String url;
    private String thumnail;
    private String decription;

    public Article() {
    }

    public Article(String title, String url, String thumnail, String decription) {
        this.title = title;
        this.url = url;
        this.thumnail = thumnail;
        this.decription = decription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumnail='" + thumnail + '\'' +
                ", decription='" + decription + '\'' +
                '}';
    }
}
