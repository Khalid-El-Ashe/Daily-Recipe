package com.example.dailyrecipe.listeners;

import com.example.dailyrecipe.models.FoodHorizontal;
import com.example.dailyrecipe.models.FoodVertical;

public interface testListener {
    void horizontalClicked(FoodHorizontal foodHorizontal);

    void verticalClicked(FoodVertical foodVertical);
}
