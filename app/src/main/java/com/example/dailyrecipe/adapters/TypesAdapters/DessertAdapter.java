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
import com.example.dailyrecipe.listeners.DessertListener;
import com.example.dailyrecipe.models.Dessert;

import java.util.List;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertViewHolder> {
    private DessertListener dessertListener;
    private List<Dessert> dessertList;

    public DessertAdapter(DessertListener dessertListener, List<Dessert> dessertList) {
        this.dessertListener = dessertListener;
        this.dessertList = dessertList;
    }

    public List<Dessert> getDessertList() {
        return dessertList;
    }

    public void setDessertList(List<Dessert> dessertList) {
        this.dessertList = dessertList;
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {
        Dessert dessert = dessertList.get(position);
        holder.bindItems(dessert);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dessertListener.onItemDessertClicked(dessert);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dessertList.size();
    }

    class DessertViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(Dessert dessert) {
            image_food.setImageResource(Integer.parseInt(dessert.getImage()));
//            image_food.setImageResource(Integer.valueOf(dessert.getImage()));
            text_food_type.setText(dessert.getType());
            text_food_name.setText(dessert.getName());
            text_food_calories.setText(String.valueOf(dessert.getCalories()));
            text_food_mins.setText(String.valueOf(dessert.getMins()));
            text_food_serving.setText(String.valueOf(dessert.getServing()));
        }
    }
}
