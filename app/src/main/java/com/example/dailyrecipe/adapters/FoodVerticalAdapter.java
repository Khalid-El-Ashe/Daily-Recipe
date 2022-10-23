package com.example.dailyrecipe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.models.FoodVertical;

import java.util.List;

public class FoodVerticalAdapter extends RecyclerView.Adapter<FoodVerticalAdapter.FoodVerticalHolder>{
    private List<FoodVertical> foodVerticalList;

    public FoodVerticalAdapter(List<FoodVertical> foodVerticalList) {
        this.foodVerticalList = foodVerticalList;
    }

    public List<FoodVertical> getFoodVerticalList() {
        return foodVerticalList;
    }

    public void setFoodVerticalList(List<FoodVertical> foodVerticalList) {
        this.foodVerticalList = foodVerticalList;
    }

    @NonNull
    @Override
    public FoodVerticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new FoodVerticalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodVerticalHolder holder, int position) {
        FoodVertical foodVertical = foodVerticalList.get(position);
        holder.bindItems(foodVertical);
    }

    @Override
    public int getItemCount() {
        return foodVerticalList.size();
    }

    class FoodVerticalHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoodVertical, imgFoodVerticalLike;
        private TextView textFoodVerticalType, textFoodVerticalName, textFoodVerticalCalories, textFoodVerticalMins, textFoodVerticalServing;

        public FoodVerticalHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodVertical = itemView.findViewById(R.id.img_vertical_food);
            imgFoodVerticalLike = itemView.findViewById(R.id.img_vertical_like);
            textFoodVerticalType = itemView.findViewById(R.id.text_food_vertical_type);
            textFoodVerticalName = itemView.findViewById(R.id.text_food_vertical_name);
            textFoodVerticalCalories = itemView.findViewById(R.id.text_food_vertical_calories);
            textFoodVerticalMins = itemView.findViewById(R.id.text_food_vertical_mins);
            textFoodVerticalServing = itemView.findViewById(R.id.text_food_vertical_serving);
        }

        private void bindItems(FoodVertical foodVertical){
            textFoodVerticalType.setText(foodVertical.getType());
            textFoodVerticalName.setText(foodVertical.getName());
            textFoodVerticalCalories.setText(foodVertical.getCalories());
            textFoodVerticalMins.setText(foodVertical.getMins());
            textFoodVerticalServing.setText(foodVertical.getServing());
        }
    }
}
