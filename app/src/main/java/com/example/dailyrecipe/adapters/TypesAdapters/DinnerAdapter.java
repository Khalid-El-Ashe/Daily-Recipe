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
import com.example.dailyrecipe.listeners.DinnerListener;
import com.example.dailyrecipe.models.Dinner;

import java.util.List;

public class DinnerAdapter extends RecyclerView.Adapter<DinnerAdapter.DinnerViewHolder> {
    private DinnerListener dinnerListener;
    private List<Dinner> dinnerList;

    public DinnerAdapter(DinnerListener dinnerListener, List<Dinner> dinnerList) {
        this.dinnerListener = dinnerListener;
        this.dinnerList = dinnerList;
    }

    public List<Dinner> getDinnerList() {
        return dinnerList;
    }

    public void setDinnerList(List<Dinner> dinnerList) {
        this.dinnerList = dinnerList;
    }

    @NonNull
    @Override
    public DinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_horizontal_item, parent, false);
        return new DinnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DinnerViewHolder holder, int position) {
        Dinner dinner = dinnerList.get(position);
        holder.bindItems(dinner);
        holder.horizontal_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dinnerListener.onItemDinnerClicked(dinner);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dinnerList.size();
    }

    class DinnerViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout horizontal_container;
        private ImageView horizontal_img_food;
        private TextView text_type, text_name, text_calories, text_mins, text_serving;

        public DinnerViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontal_container = itemView.findViewById(R.id.horizontal_container);
            horizontal_img_food = itemView.findViewById(R.id.image_food);
            text_type = itemView.findViewById(R.id.text_food_type);
            text_name = itemView.findViewById(R.id.text_food_name);
            text_calories = itemView.findViewById(R.id.text_food_calories);
            text_mins = itemView.findViewById(R.id.text_food_mins);
            text_serving = itemView.findViewById(R.id.text_food_serving);

        }

        void bindItems(Dinner dinner) {
            horizontal_img_food.setImageResource(Integer.parseInt(dinner.getImage()));
//            horizontal_img_food.setImageResource(Integer.valueOf(dinner.getImage()));
            text_type.setText(dinner.getType());
            text_name.setText(dinner.getName());
            text_calories.setText(String.valueOf(dinner.getCalories()));
            text_mins.setText(String.valueOf(dinner.getMins()));
            text_serving.setText(String.valueOf(dinner.getServing()));
        }
    }
}
