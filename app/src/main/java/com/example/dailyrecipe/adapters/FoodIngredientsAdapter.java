package com.example.dailyrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.models.Ingredients;

import java.util.List;

public class FoodIngredientsAdapter extends RecyclerView.Adapter<FoodIngredientsAdapter.FoodIngredientHolder> {

    private List<Ingredients> ingredientsList;

    public FoodIngredientsAdapter(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public FoodIngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_ingredients, parent, false);
        return new FoodIngredientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodIngredientHolder holder, int position) {
        Ingredients ingredients = ingredientsList.get(position);
        holder.bindItem(ingredients);
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class FoodIngredientHolder extends RecyclerView.ViewHolder {
        private ImageView img_ingredient;

        public FoodIngredientHolder(@NonNull View itemView) {
            super(itemView);
            img_ingredient = itemView.findViewById(R.id.item_food_ingredients);
        }

        private void bindItem(Ingredients ingredients) {
            img_ingredient.setImageResource(Integer.valueOf(ingredients.getImage()));
        }
    }
}
