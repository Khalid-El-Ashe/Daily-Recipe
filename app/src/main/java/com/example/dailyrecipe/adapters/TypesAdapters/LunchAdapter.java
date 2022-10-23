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
import com.example.dailyrecipe.listeners.LunchListener;
import com.example.dailyrecipe.models.Lunch;

import java.util.List;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.LunchViewHolder> {

    private LunchListener lunchListener;
    private List<Lunch> lunchList;

    public LunchAdapter(LunchListener lunchListener, List<Lunch> lunchList) {
        this.lunchListener = lunchListener;
        this.lunchList = lunchList;
    }

    public List<Lunch> getLunchList() {
        return lunchList;
    }

    public void setLunchList(List<Lunch> lunchList) {
        this.lunchList = lunchList;
    }

    @NonNull
    @Override
    public LunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_horizontal_item, parent, false);
        return new LunchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LunchViewHolder holder, int position) {
        Lunch lunch = lunchList.get(position);
        holder.bindItems(lunch);
        holder.horizontal_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunchListener.onItemLunchClicked(lunch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lunchList.size();
    }

    class LunchViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout horizontal_container;
        private ImageView horizontal_img_food;
        private TextView text_type, text_name, text_calories, text_mins, text_serving;

        public LunchViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontal_container = itemView.findViewById(R.id.horizontal_container);
            horizontal_img_food = itemView.findViewById(R.id.image_food);
            text_type = itemView.findViewById(R.id.text_food_type);
            text_name = itemView.findViewById(R.id.text_food_name);
            text_calories = itemView.findViewById(R.id.text_food_calories);
            text_mins = itemView.findViewById(R.id.text_food_mins);
            text_serving = itemView.findViewById(R.id.text_food_serving);

        }

        void bindItems(Lunch lunch) {
            horizontal_img_food.setImageResource(Integer.parseInt(lunch.getImage()));
//            horizontal_img_food.setImageResource(Integer.valueOf(lunch.getImage()));
            text_type.setText(lunch.getType());
            text_name.setText(lunch.getName());
            text_calories.setText(String.valueOf(lunch.getCalories()));
            text_mins.setText(String.valueOf(lunch.getMins()));
            text_serving.setText(String.valueOf(lunch.getServing()));
        }
    }
}
