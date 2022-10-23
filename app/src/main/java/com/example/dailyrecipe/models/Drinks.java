package com.example.dailyrecipe.models;

import java.io.Serializable;

public class Drinks implements Serializable {
    private String image;
    private String type;
    private String name;
    private int calories;
    private int mins;
    private int serving;
    private String description;

    public Drinks() {
    }

    public Drinks(String image, String type, String name, int calories, int mins, int serving, String description) {
        this.image = image;
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.mins = mins;
        this.serving = serving;
        this.description = description;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
