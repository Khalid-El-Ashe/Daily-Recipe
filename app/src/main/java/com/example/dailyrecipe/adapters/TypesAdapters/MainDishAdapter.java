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
import com.example.dailyrecipe.listeners.MainDishListener;
import com.example.dailyrecipe.models.MainDish;
import com.example.dailyrecipe.models.Starter;

import java.util.List;

public class MainDishAdapter extends RecyclerView.Adapter<MainDishAdapter.MainDishViewHolder> {
    private MainDishListener mainDishListener;
    private List<MainDish> mainDishList;

    public MainDishAdapter(MainDishListener mainDishListener, List<MainDish> mainDishList) {
        this.mainDishListener = mainDishListener;
        this.mainDishList = mainDishList;
    }

    public List<MainDish> getMainDishList() {
        return mainDishList;
    }

    public void setMainDishList(List<MainDish> mainDishList) {
        this.mainDishList = mainDishList;
    }

    @NonNull
    @Override
    public MainDishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new MainDishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainDishViewHolder holder, int position) {
        MainDish mainDish = mainDishList.get(position);
        holder.bindItems(mainDish);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainDishListener.onItemMainDishClicked(mainDish);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainDishList.size();
    }

    class MainDishViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public MainDishViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(MainDish mainDish) {
            image_food.setImageResource(Integer.parseInt(mainDish.getImage()));
//            image_food.setImageResource(Integer.valueOf(mainDish.getImage()));
            text_food_type.setText(mainDish.getType());
            text_food_name.setText(mainDish.getName());
            text_food_calories.setText(String.valueOf(mainDish.getCalories()));
            text_food_mins.setText(String.valueOf(mainDish.getMins()));
            text_food_serving.setText(String.valueOf(mainDish.getServing()));
        }
    }
}
