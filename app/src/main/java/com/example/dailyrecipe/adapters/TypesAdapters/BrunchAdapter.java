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
import com.example.dailyrecipe.listeners.BrunchListener;
import com.example.dailyrecipe.models.Brunch;

import java.util.List;

public class BrunchAdapter extends RecyclerView.Adapter<BrunchAdapter.BrunchViewHolder> {

    private BrunchListener brunchListener;
    private List<Brunch> brunchList;

    public BrunchAdapter(BrunchListener brunchListener, List<Brunch> brunchList) {
        this.brunchListener = brunchListener;
        this.brunchList = brunchList;
    }

    public List<Brunch> getBrunchList() {
        return brunchList;
    }

    public void setBrunchList(List<Brunch> brunchList) {
        this.brunchList = brunchList;
    }

    @NonNull
    @Override
    public BrunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_horizontal_item, parent, false);
        return new BrunchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrunchViewHolder holder, int position) {
        Brunch brunch = brunchList.get(position);
        holder.bindItems(brunch);
        holder.horizontal_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brunchListener.onItemBrunchClicked(brunch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brunchList.size();
    }

    class BrunchViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout horizontal_container;
        private ImageView horizontal_img_food;
        private TextView text_type, text_name, text_calories, text_mins, text_serving;

        public BrunchViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontal_container = itemView.findViewById(R.id.horizontal_container);
            horizontal_img_food = itemView.findViewById(R.id.image_food);
            text_type = itemView.findViewById(R.id.text_food_type);
            text_name = itemView.findViewById(R.id.text_food_name);
            text_calories = itemView.findViewById(R.id.text_food_calories);
            text_mins = itemView.findViewById(R.id.text_food_mins);
            text_serving = itemView.findViewById(R.id.text_food_serving);

        }

        void bindItems(Brunch brunch) {
            horizontal_img_food.setImageResource(Integer.parseInt(brunch.getImage()));
//            horizontal_img_food.setImageResource(Integer.valueOf(brunch.getImage()));
            text_type.setText(brunch.getType());
            text_name.setText(brunch.getName());
            text_calories.setText(String.valueOf(brunch.getCalories()));
            text_mins.setText(String.valueOf(brunch.getMins()));
            text_serving.setText(String.valueOf(brunch.getServing()));
        }
    }
}
