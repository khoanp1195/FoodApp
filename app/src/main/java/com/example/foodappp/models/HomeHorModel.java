package com.example.foodappp.models;

public class HomeHorModel {

   String image;
    String name;
    String type;

    public HomeHorModel() {
    }

    public HomeHorModel(String image, String name, String type) {
        this.image = image;
        this.name = name;
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public HomeHorModel setImage(String image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public HomeHorModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public HomeHorModel setType(String type) {
        this.type = type;
        return this;
    }
}
