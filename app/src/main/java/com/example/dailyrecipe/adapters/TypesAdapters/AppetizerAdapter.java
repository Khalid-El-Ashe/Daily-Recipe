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
import com.example.dailyrecipe.listeners.AppetizerListener;
import com.example.dailyrecipe.models.Appetizer;

import java.util.List;

public class AppetizerAdapter extends RecyclerView.Adapter<AppetizerAdapter.AppetizerViewHolder> {
    private AppetizerListener appetizerListener;
    private List<Appetizer> appetizerList;

    public AppetizerAdapter(List<Appetizer> appetizerList) {
        this.appetizerList = appetizerList;
    }

    public AppetizerAdapter(AppetizerListener appetizerListener, List<Appetizer> appetizerList) {
        this.appetizerListener = appetizerListener;
        this.appetizerList = appetizerList;
    }

    public void setAppetizerList(List<Appetizer> appetizerList) {
        this.appetizerList = appetizerList;
    }

    @NonNull
    @Override
    public AppetizerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new AppetizerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppetizerViewHolder holder, int position) {
        Appetizer appetizer = appetizerList.get(position);
        holder.bindItems(appetizer);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appetizerListener.onItemAppetizerClicked(appetizer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appetizerList.size();
    }

    class AppetizerViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public AppetizerViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(Appetizer appetizer) {
            image_food.setImageResource(Integer.parseInt(appetizer.getImage()));
//            image_food.setImageResource(Integer.valueOf(appetizer.getImage()));
            text_food_type.setText(appetizer.getType());
            text_food_name.setText(appetizer.getName());
            text_food_calories.setText(String.valueOf(appetizer.getCalories()));
            text_food_mins.setText(String.valueOf(appetizer.getMins()));
            text_food_serving.setText(String.valueOf(appetizer.getServing()));
        }
    }
}
