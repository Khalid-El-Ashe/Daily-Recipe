package com.example.dailyrecipe.models;

import java.io.Serializable;

public class FoodHorizontal implements Serializable {
    private String image;
    private String type;
    private String name;
    private String calories;
    private String mins;
    private String serving;

    public FoodHorizontal() {
    }

    public FoodHorizontal(String image, String type, String name, String calories, String mins, String serving) {
        this.image = image;
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.mins = mins;
        this.serving = serving;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getMins() {
        return mins;
    }

    public void setMins(String mins) {
        this.mins = mins;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }
}
