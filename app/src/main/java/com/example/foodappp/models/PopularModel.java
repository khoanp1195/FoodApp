package com.example.foodappp.models;

import java.io.Serializable;

public class PopularModel implements Serializable {
    String name;
    int price;
    String img_url;

    public PopularModel() {
    }

    public PopularModel(String name, int price, String img_url) {
        this.name = name;
        this.price = price;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public PopularModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public PopularModel setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImg_url() {
        return img_url;
    }

    public PopularModel setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }
}
