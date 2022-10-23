package com.example.dailyrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.models.Favorites;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<Favorites> favoritesList;

    public FavoritesAdapter(List<Favorites> favoritesList) {
        this.favoritesList = favoritesList;
    }

    public List<Favorites> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(List<Favorites> favoritesList) {
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_favorites, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Favorites favorites = favoritesList.get(position);
        holder.bindItem(favorites);
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_fav_food, img_fav_liked;
        private TextView text_fav_type, text_fav_name, text_fav_calories, text_fav_mins, text_fav_serving;


        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);

            img_fav_food = itemView.findViewById(R.id.image_fav_food);
            img_fav_liked = itemView.findViewById(R.id.img_fav_like);
            text_fav_type = itemView.findViewById(R.id.text_fav_type);
            text_fav_name = itemView.findViewById(R.id.text_fav_name);
            text_fav_calories = itemView.findViewById(R.id.text_fav_calories);
            text_fav_mins = itemView.findViewById(R.id.text_fav_mins);
            text_fav_serving = itemView.findViewById(R.id.text_fav_serving);
        }

        private void bindItem(Favorites favorites) {
            img_fav_food.setImageResource(Integer.valueOf(favorites.getImage_food()));
            text_fav_type.setText(favorites.getFav_type());
            text_fav_name.setText(favorites.getFav_name());
            text_fav_calories.setText(favorites.getFav_calories());
            text_fav_mins.setText(favorites.getFav_mins());
            text_fav_serving.setText(favorites.getFav_serving());
        }
    }
}
