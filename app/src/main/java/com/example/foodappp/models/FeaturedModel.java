package com.example.foodappp.models;

public class FeaturedModel {
    String Img_url;
    String name;
    String description;
    String type;

    public FeaturedModel() {
    }

    public FeaturedModel(String img_url, String name, String description, String type) {
        Img_url = img_url;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getImg_url() {
        return Img_url;
    }

    public FeaturedModel setImg_url(String img_url) {
        Img_url = img_url;
        return this;
    }

    public String getName() {
        return name;
    }

    public FeaturedModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FeaturedModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getType() {
        return type;
    }

    public FeaturedModel setType(String type) {
        this.type = type;
        return this;
    }


}
