package com.example.dailyrecipe.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.adapters.FavoritesAdapter;
import com.example.dailyrecipe.models.Favorites;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Favorites> favoritesList;
    private FavoritesAdapter adapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        drawerLayout = view.findViewById(R.id.layout_favorites_drawer);
        navigationView = view.findViewById(R.id.side_menu_navigation);
        imageView = view.findViewById(R.id.img_fav_side_menu);
        searchView = view.findViewById(R.id.search_favorites);
        recyclerView = view.findViewById(R.id.recycler_favorites);

        initializationRecyclerFav();

        imageView.setOnClickListener(view1 -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layout_container, new HomeFragment())
                                .addToBackStack(null).commit();
                        break;


                    case R.id.nav_setting:
                        FragmentTransaction fragmentSetting = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentSetting.replace(R.id.layout_container, new SettingFragment())
                                .addToBackStack(null).commit();
                        break;

                    case R.id.nav_about:
                        break;

                    case R.id.nav_help:
                        break;

                    case R.id.nav_out:
                        FirebaseAuth.getInstance().signOut();
                        break;

                }

                return false;
            }
        });

        return view;

    }

    private void initializationRecyclerFav() {
        favoritesList = new ArrayList<>();
        adapter = new FavoritesAdapter(favoritesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
    }
}