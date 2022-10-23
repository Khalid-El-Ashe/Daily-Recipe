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
import com.example.dailyrecipe.listeners.SideListener;
import com.example.dailyrecipe.models.Side;

import java.util.List;

public class SideAdapter extends RecyclerView.Adapter<SideAdapter.SideViewHolder> {
    private SideListener sideListener;
    private List<Side> sideList;

    public SideAdapter(SideListener sideListener, List<Side> sideList) {
        this.sideListener = sideListener;
        this.sideList = sideList;
    }

    public List<Side> getSideList() {
        return sideList;
    }

    public void setSideList(List<Side> sideList) {
        this.sideList = sideList;
    }

    @NonNull
    @Override
    public SideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new SideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SideViewHolder holder, int position) {
        Side side = sideList.get(position);
        holder.bindItems(side);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sideListener.onItemSideClicked(side);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sideList.size();
    }

    class SideViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public SideViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(Side side) {
            image_food.setImageResource(Integer.parseInt(side.getImage()));
//            image_food.setImageResource(Integer.valueOf(side.getImage()));
            text_food_type.setText(side.getType());
            text_food_name.setText(side.getName());
            text_food_calories.setText(String.valueOf(side.getCalories()));
            text_food_mins.setText(String.valueOf(side.getMins()));
            text_food_serving.setText(String.valueOf(side.getServing()));
        }
    }
}
