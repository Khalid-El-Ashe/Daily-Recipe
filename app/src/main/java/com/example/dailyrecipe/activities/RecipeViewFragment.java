package com.example.dailyrecipe.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.adapters.FoodIngredientsAdapter;
import com.example.dailyrecipe.models.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewFragment extends Fragment {
    private RecyclerView recyclerView_recipe;
    private ImageView image_back;

    private List<Ingredients> ingredients;
    private FoodIngredientsAdapter adapter;

    public RecipeViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_view, container, false);

        recyclerView_recipe = view.findViewById(R.id.recycler_recipe_food_ingredients);
        image_back = view.findViewById(R.id.img_back);

        ingredients = new ArrayList<>();
        ingredients.add(new Ingredients(String.valueOf(R.drawable.mask_group7)));
        ingredients.add(new Ingredients(String.valueOf(R.drawable.mask_group3)));
        ingredients.add(new Ingredients(String.valueOf(R.drawable.mask_group4)));
        ingredients.add(new Ingredients(String.valueOf(R.drawable.mask_group8)));
        ingredients.add(new Ingredients(String.valueOf(R.drawable.mask_group5)));
        ingredients.add(new Ingredients(String.valueOf(R.drawable.mask_group6)));

        adapter = new FoodIngredientsAdapter(ingredients);
        recyclerView_recipe.setAdapter(adapter);
        recyclerView_recipe.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_recipe.setHasFixedSize(true);


        image_back.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null).commit();
        });

        return view;
    }

}