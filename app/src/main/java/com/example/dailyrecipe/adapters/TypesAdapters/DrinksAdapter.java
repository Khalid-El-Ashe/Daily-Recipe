package com.example.dailyrecipe.adapters.TypesAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.listeners.DrinksListener;
import com.example.dailyrecipe.models.Drinks;
import com.example.dailyrecipe.models.MainDish;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder> {
    private DrinksListener drinksListener;
    private List<Drinks> drinksList;

    public DrinksAdapter(DrinksListener drinksListener, List<Drinks> drinksList) {
        this.drinksListener = drinksListener;
        this.drinksList = drinksList;
    }

    public List<Drinks> getDrinksList() {
        return drinksList;
    }

    public void setDrinksList(List<Drinks> drinksList) {
        this.drinksList = drinksList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drinks drinks = drinksList.get(position);
        holder.bindItems(drinks);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinksListener.onItemDrinkClicked(drinks);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }

    class DrinkViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(Drinks drinks) {
            image_food.setImageResource(Integer.parseInt(drinks.getImage()));
//            image_food.setImageResource(Integer.valueOf(drinks.getImage()));
            text_food_type.setText(drinks.getType());
            text_food_name.setText(drinks.getName());
            text_food_calories.setText(String.valueOf(drinks.getCalories()));
            text_food_mins.setText(String.valueOf(drinks.getMins()));
            text_food_serving.setText(String.valueOf(drinks.getServing()));
        }
    }
}
