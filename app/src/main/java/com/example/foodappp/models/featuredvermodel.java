package com.example.foodappp.models;

public class featuredvermodel {

    String name;
    String img_url;
    String rating;
    String description;
    String time;
    String type;

    public featuredvermodel() {
    }

    public featuredvermodel(String name, String img_url, String rating, String description, String time, String type) {
        this.name = name;
        this.img_url = img_url;
        this.rating = rating;
        this.description = description;
        this.time = time;
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public featuredvermodel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public featuredvermodel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg_url() {
        return img_url;
    }

    public featuredvermodel setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public featuredvermodel setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getTime() {
        return time;
    }

    public featuredvermodel setTime(String time) {
        this.time = time;
        return this;
    }

    public String getType() {
        return type;
    }

    public featuredvermodel setType(String type) {
        this.type = type;
        return this;
    }
}
