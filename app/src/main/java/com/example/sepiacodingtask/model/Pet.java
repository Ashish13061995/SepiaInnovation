
package com.example.sepiacodingtask.model;


import com.google.gson.annotations.SerializedName;

public class Pet {

    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("content_url")
    private String contentUrl;
    @SerializedName("date_added")
    private String dateAdded;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

}
