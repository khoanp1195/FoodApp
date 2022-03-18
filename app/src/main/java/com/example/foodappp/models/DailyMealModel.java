package com.example.foodappp.models;

public class DailyMealModel {
    String img_url;
    String description;
    String name;
    String discount;
    String type;

    public DailyMealModel() {
    }

    public DailyMealModel(String img_url, String description, String name, String discount, String type) {
        this.img_url = img_url;
        this.description = description;
        this.name = name;
        this.discount = discount;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public DailyMealModel setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DailyMealModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public DailyMealModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public DailyMealModel setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getType() {
        return type;
    }

    public DailyMealModel setType(String type) {
        this.type = type;
        return this;
    }
}
