package com.example.foodappp.models;

public class HomeViewModel {
    String img_url;
    String name;
    String timing;
    String rating;
    String price;


    public HomeViewModel() {
    }

    public HomeViewModel(String img_url, String name, String timing, String rating, String price) {
        this.img_url = img_url;
        this.name = name;
        this.timing = timing;
        this.rating = rating;
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public HomeViewModel setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }

    public String getName() {
        return name;
    }

    public HomeViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getTiming() {
        return timing;
    }

    public HomeViewModel setTiming(String timing) {
        this.timing = timing;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public HomeViewModel setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public HomeViewModel setPrice(String price) {
        this.price = price;
        return this;
    }
}
