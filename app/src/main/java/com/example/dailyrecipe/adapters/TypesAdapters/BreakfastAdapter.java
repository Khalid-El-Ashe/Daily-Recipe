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
import com.example.dailyrecipe.listeners.BreakfastListener;
import com.example.dailyrecipe.models.Breakfast;

import java.util.List;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastViewHolder> {

    private BreakfastListener breakfastListener;

    private List<Breakfast> breakfastList;

    public BreakfastAdapter(BreakfastListener breakfastListener, List<Breakfast> breakfastList) {
        this.breakfastListener = breakfastListener;
        this.breakfastList = breakfastList;
    }

    public List<Breakfast> getBreakfastList() {
        return breakfastList;
    }

    public void setBreakfastList(List<Breakfast> breakfastList) {
        this.breakfastList = breakfastList;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_horizontal_item, parent, false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {
        Breakfast breakfast = breakfastList.get(position);
        holder.bindItems(breakfast);
        holder.horizontal_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakfastListener.onItemBreakfastClicked(breakfast);
            }
        });
    }

    @Override
    public int getItemCount() {
        return breakfastList.size();
    }

    class BreakfastViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout horizontal_container;
        private ImageView horizontal_img_food;
        private TextView text_type, text_name, text_calories, text_mins, text_serving;

        public BreakfastViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontal_container = itemView.findViewById(R.id.horizontal_container);
            horizontal_img_food = itemView.findViewById(R.id.image_food);
            text_type = itemView.findViewById(R.id.text_food_type);
            text_name = itemView.findViewById(R.id.text_food_name);
            text_calories = itemView.findViewById(R.id.text_food_calories);
            text_mins = itemView.findViewById(R.id.text_food_mins);
            text_serving = itemView.findViewById(R.id.text_food_serving);

        }

        void bindItems(Breakfast breakfast) {
            horizontal_img_food.setImageResource(Integer.parseInt(breakfast.getImage()));
//            horizontal_img_food.setImageResource(Integer.valueOf(breakfast.getImage()));
            text_type.setText(breakfast.getType());
            text_name.setText(breakfast.getName());
            text_calories.setText(String.valueOf(breakfast.getCalories()));
            text_mins.setText(String.valueOf(breakfast.getMins()));
            text_serving.setText(String.valueOf(breakfast.getServing()));
        }
    }
}
