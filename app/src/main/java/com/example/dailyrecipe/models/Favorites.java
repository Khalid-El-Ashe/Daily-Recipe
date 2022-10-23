package com.example.dailyrecipe.models;

import java.io.Serializable;

public class Favorites implements Serializable {
    private String image_food;
    private String image_like;
    private String fav_type;
    private String fav_name;
    private String fav_calories;
    private String fav_mins;
    private String fav_serving;

    public Favorites() {
    }

    public Favorites(String image_food, String image_like, String fav_type, String fav_name, String fav_calories, String fav_mins, String fav_serving) {
        this.image_food = image_food;
        this.image_like = image_like;
        this.fav_type = fav_type;
        this.fav_name = fav_name;
        this.fav_calories = fav_calories;
        this.fav_mins = fav_mins;
        this.fav_serving = fav_serving;
    }

    public String getImage_food() {
        return image_food;
    }

    public void setImage_food(String image_food) {
        this.image_food = image_food;
    }

    public String getImage_like() {
        return image_like;
    }

    public void setImage_like(String image_like) {
        this.image_like = image_like;
    }

    public String getFav_type() {
        return fav_type;
    }

    public void setFav_type(String fav_type) {
        this.fav_type = fav_type;
    }

    public String getFav_name() {
        return fav_name;
    }

    public void setFav_name(String fav_name) {
        this.fav_name = fav_name;
    }

    public String getFav_calories() {
        return fav_calories;
    }

    public void setFav_calories(String fav_calories) {
        this.fav_calories = fav_calories;
    }

    public String getFav_mins() {
        return fav_mins;
    }

    public void setFav_mins(String fav_mins) {
        this.fav_mins = fav_mins;
    }

    public String getFav_serving() {
        return fav_serving;
    }

    public void setFav_serving(String fav_serving) {
        this.fav_serving = fav_serving;
    }
}
