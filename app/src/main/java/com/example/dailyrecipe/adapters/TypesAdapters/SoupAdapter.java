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
import com.example.dailyrecipe.listeners.SoupListener;
import com.example.dailyrecipe.models.Soup;

import java.util.List;

public class SoupAdapter extends RecyclerView.Adapter<SoupAdapter.SoupViewHolder> {

    private SoupListener soupListener;
    private List<Soup> soupList;

    public SoupAdapter(SoupListener soupListener, List<Soup> soupList) {
        this.soupListener = soupListener;
        this.soupList = soupList;
    }

    public List<Soup> getSoupList() {
        return soupList;
    }

    public void setSoupList(List<Soup> soupList) {
        this.soupList = soupList;
    }

    @NonNull
    @Override
    public SoupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_verticaly_item, parent, false);
        return new SoupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoupViewHolder holder, int position) {
        Soup soup = soupList.get(position);
        holder.bindItems(soup);
        holder.vertical_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soupListener.onItemSoupClicked(soup);
            }
        });
    }

    @Override
    public int getItemCount() {
        return soupList.size();
    }

    class SoupViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout vertical_container;
        private ImageView image_food;
        private TextView text_food_type, text_food_name, text_food_calories, text_food_mins, text_food_serving;

        public SoupViewHolder(@NonNull View itemView) {
            super(itemView);
            vertical_container = itemView.findViewById(R.id.vertical_container);
            image_food = itemView.findViewById(R.id.img_vertical_food);
            text_food_type = itemView.findViewById(R.id.text_food_vertical_type);
            text_food_name = itemView.findViewById(R.id.text_food_vertical_name);
            text_food_calories = itemView.findViewById(R.id.text_food_vertical_calories);
            text_food_mins = itemView.findViewById(R.id.text_food_vertical_mins);
            text_food_serving = itemView.findViewById(R.id.text_food_vertical_serving);

        }

        void bindItems(Soup soup) {
            image_food.setImageResource(Integer.parseInt(soup.getImage()));
//            image_food.setImageResource(Integer.valueOf(soup.getImage()));
            text_food_type.setText(soup.getType());
            text_food_name.setText(soup.getName());
            text_food_calories.setText(String.valueOf(soup.getCalories()));
            text_food_mins.setText(String.valueOf(soup.getMins()));
            text_food_serving.setText(String.valueOf(soup.getServing()));
        }
    }
}
