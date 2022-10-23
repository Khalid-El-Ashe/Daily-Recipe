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
import com.example.dailyrecipe.listeners.StarterListener;
import com.example.dailyrecipe.models.Starter;

import java.util.List;

public class StarterAdapter extends RecyclerView.Adapter<StarterAdapter.StarterViewHolder> {
    private StarterListener starterListener;
    private List<Starter> starterList;

    public StarterAdapter(StarterListener starterListener, List<Starter> starterList) {
        this.starterListener = starterListener;
        this.starterList = starterList;
    }

    public List<Starter> getStarterList() {
        return starterList;
    }

    public void setStarterList(List<Starter> starterList) {
        this.starterList = starterList;
    }

    @NonNull
    @Override
    public StarterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new StarterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarterViewHolder holder, int position) {
        Starter starter = starterList.get(position);
        holder.bindItems(starter);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starterListener.onItemStarterClicked(starter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return starterList.size();
    }

    class StarterViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public StarterViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(Starter starter) {
            image_food.setImageResource(Integer.parseInt(starter.getImage()));
//            image_food.setImageResource(Integer.valueOf(starter.getImage()));
            text_food_type.setText(starter.getType());
            text_food_name.setText(starter.getName());
            text_food_calories.setText(String.valueOf(starter.getCalories()));
            text_food_mins.setText(String.valueOf(starter.getMins()));
            text_food_serving.setText(String.valueOf(starter.getServing()));
        }
    }
}
