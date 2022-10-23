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
import com.example.dailyrecipe.models.FoodHorizontal;

import java.util.List;

public class FoodHorizontalAdapter extends RecyclerView.Adapter<FoodHorizontalAdapter.FoodHorizontalHolder> {

    private List<FoodHorizontal> foodHorizontalList;

    public FoodHorizontalAdapter(List<FoodHorizontal> foodHorizontalList) {
        this.foodHorizontalList = foodHorizontalList;
    }

    public List<FoodHorizontal> getFoodHorizontalList() {
        return foodHorizontalList;
    }

    public void setFoodHorizontalList(List<FoodHorizontal> foodHorizontalList) {
        this.foodHorizontalList = foodHorizontalList;
    }

    @NonNull
    @Override
    public FoodHorizontalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_horizontal_item, parent, false);
        return new FoodHorizontalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHorizontalHolder holder, int position) {
        FoodHorizontal foodHorizontal = foodHorizontalList.get(position);
        holder.bindItems(foodHorizontal);
    }

    @Override
    public int getItemCount() {
        return foodHorizontalList.size();
    }

    class FoodHorizontalHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoodHorizontal, imgFoodHorizontalLike;
        private TextView textFoodType, textFoodName, textFoodCalories, textFoodMins, textFoodServing;

        public FoodHorizontalHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodHorizontal = itemView.findViewById(R.id.image_food);
            imgFoodHorizontalLike = itemView.findViewById(R.id.img_like);
            textFoodType = itemView.findViewById(R.id.text_food_type);
            textFoodName = itemView.findViewById(R.id.text_food_name);
            textFoodCalories = itemView.findViewById(R.id.text_food_calories);
            textFoodMins = itemView.findViewById(R.id.text_food_mins);
            textFoodServing = itemView.findViewById(R.id.text_food_serving);
        }

        private void bindItems(FoodHorizontal foodHorizontal) {
            textFoodType.setText(foodHorizontal.getType());
            textFoodName.setText(foodHorizontal.getName());
            textFoodCalories.setText(foodHorizontal.getCalories());
            textFoodMins.setText(foodHorizontal.getMins());
            textFoodServing.setText(foodHorizontal.getServing());
        }
    }
}
