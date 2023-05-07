package com.example.sim;

import java.io.Serializable;

public class itemsDomain implements Serializable {
    private String title;
    private String description;
    private String image;
    private String date;

    public itemsDomain(String title, String description, String image, String date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {

    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {

    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {

    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {

    }

}
