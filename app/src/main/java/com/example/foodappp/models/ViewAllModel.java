package com.example.foodappp.models;

public class ViewAllModel {
    String name;
    String timing;
    String rating;
    int price;
    String img_url;
    String type;
    String description;


    public ViewAllModel() {
    }

    public ViewAllModel(String name, String timing, String rating, int price, String img_url, String type, String description) {
        this.name = name;
        this.timing = timing;
        this.rating = rating;
        this.price = price;
        this.img_url = img_url;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public ViewAllModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getTiming() {
        return timing;
    }

    public ViewAllModel setTiming(String timing) {
        this.timing = timing;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public ViewAllModel setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public ViewAllModel setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImg_url() {
        return img_url;
    }

    public ViewAllModel setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }

    public String getType() {
        return type;
    }

    public ViewAllModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ViewAllModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
