package com.example.trafalgar.martimage;

import java.io.Serializable;

/**
 * Created by fdelgado on 8/2/18.
 */

public class Photo implements Serializable {

    private static final long serialVersionUID = 3809936409831543140L;

    private String mTitle;
    private String mLink;
    private String mAuthor;
    private String mImage;


    public Photo(String title, String link, String author, String image) {
        mTitle = title;
        mLink = link;
        mAuthor = author;
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getImage() {
        return mImage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
