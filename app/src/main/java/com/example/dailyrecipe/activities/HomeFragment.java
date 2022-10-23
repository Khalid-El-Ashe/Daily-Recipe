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
import android.widget.Button;

import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.adapters.FoodHorizontalAdapter;
import com.example.dailyrecipe.adapters.FoodVerticalAdapter;
import com.example.dailyrecipe.models.FoodHorizontal;
import com.example.dailyrecipe.models.FoodVertical;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentTransaction fragmentTransaction, fragmentSetting, fragmentSignOut, fragmentAboutUS, fragmentHelp;

    private DrawerLayout drawerLayout;
    private ImageView img_side_menu, img_filter, im_notification, img_add_item;
    private SearchView searchView;
    private NavigationView navigationView;
    private RecyclerView recyclerViewHorizontal, recyclerViewVertical;

    private TextView text_reset;
    private Button btn_filter_breakfast, btn_filter_brunch, btn_filter_lunch, btn_filter_dinner;

    private FoodHorizontalAdapter horizontalAdapter;
    private FoodVerticalAdapter verticalAdapter;

    private List<FoodHorizontal> foodHorizontalList;
    private List<FoodVertical> foodVerticalList;

    public static final int BREAKFAST_REQUEST = 1;
    public static final int BRUNCH_REQUEST = 1;
    public static final int LUNCH_REQUEST = 1;
    public static final int DINNER_REQUEST = 1;
    public static final int SOUP_REQUEST = 1;
    public static final int APPETIZER_REQUEST = 1;
    public static final int STARTER_REQUEST = 1;
    public static final int MAINDISH_REQUEST = 1;
    public static final int SIDE_REQUEST = 1;
    public static final int DESSERT_REQUEST = 1;
    public static final int DRINKS_REQUEST = 1;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        img_side_menu = view.findViewById(R.id.img_side_menu);
        navigationView = view.findViewById(R.id.side_menu_navigation);
        recyclerViewHorizontal = view.findViewById(R.id.food_item_horizontal);
        recyclerViewVertical = view.findViewById(R.id.food_item_vertical);
        img_filter = view.findViewById(R.id.img_filter);
        im_notification = view.findViewById(R.id.img_notification_side_menu);
        img_add_item = view.findViewById(R.id.img_add_new_item);

        foodHorizontalList = new ArrayList<>();
        foodHorizontalList.add(new FoodHorizontal(String.valueOf(R.drawable.image), "Breakfast", "French Toast with \n" +
                "Berries", "120 calories", "10 mins", "1 serving"));

        foodHorizontalList.add(new FoodHorizontal(String.valueOf(R.drawable.image), "Breakfast", "French Toast with \n" +
                "Berries", "120 calories", "10 mins", "1 serving"));

        foodHorizontalList.add(new FoodHorizontal(String.valueOf(R.drawable.image), "Breakfast", "French Toast with \n" +
                "Berries", "120 calories", "10 mins", "1 serving"));

        foodVerticalList = new ArrayList<>();
        foodVerticalList.add(new FoodVertical(String.valueOf(R.drawable.image), "Breakfast", "French Toast with \n" +
                "Berries", "120 calories", "10 mins", "1 serving"));

        foodVerticalList.add(new FoodVertical(String.valueOf(R.drawable.image), "Breakfast", "French Toast with \n" +
                "Berries", "120 calories", "10 mins", "1 serving"));

        foodVerticalList.add(new FoodVertical(String.valueOf(R.drawable.image), "Breakfast", "French Toast with \n" +
                "Berries", "120 calories", "10 mins", "1 serving"));

        horizontalAdapter = new FoodHorizontalAdapter(foodHorizontalList);
        verticalAdapter = new FoodVerticalAdapter(foodVerticalList);

        recyclerViewHorizontal.setAdapter(horizontalAdapter);
        recyclerViewHorizontal.setHasFixedSize(true);
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewVertical.setAdapter(verticalAdapter);
        recyclerViewVertical.setHasFixedSize(true);
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        img_side_menu.setOnClickListener(view1 -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        navigationView.setItemIconTintList(null);

        im_notification.setOnClickListener(view1 -> {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_container, new NotificationsFragment()).addToBackStack(null).commit();
        });

        img_add_item.setOnClickListener(view1 -> {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_container, new AddNewItemFragment()).addToBackStack(null).commit();
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_favorites:
                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layout_container, new FavoritesFragment())
                                .addToBackStack(null).commit();
                        break;

                    case R.id.nav_setting:
                        fragmentSetting = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentSetting.replace(R.id.layout_container, new SettingFragment())
                                .addToBackStack(null).commit();
                        break;

                    case R.id.nav_about:
                        fragmentAboutUS = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentAboutUS.replace(R.id.layout_container, new AboutUsFragment())
                                .addToBackStack(null).commit();
                        break;

                    case R.id.nav_help:
                        break;

                    case R.id.nav_out:
                        FirebaseAuth.getInstance().signOut();
                        fragmentSignOut = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentSignOut.replace(R.id.layout_container, new SplashFragment()).commit();
                        break;

                }

                return false;
            }
        });

        img_filter.setOnClickListener(view1 -> {
            bottomSheetDialog();

        });


        return view;
    }

    private void bottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet_filter);

        // to initialize the items from bottomSheet layout
        text_reset = bottomSheetDialog.findViewById(R.id.text_reset_filter);
        btn_filter_breakfast = bottomSheetDialog.findViewById(R.id.btn_filter_breakfast);
        btn_filter_brunch = bottomSheetDialog.findViewById(R.id.btn_filter_brunch);
        btn_filter_lunch = bottomSheetDialog.findViewById(R.id.btn_filter_lunch);
        btn_filter_dinner = bottomSheetDialog.findViewById(R.id.btn_filter_dinner);

        text_reset.setOnClickListener(view -> {

        });

        btn_filter_breakfast.setOnClickListener(view -> {
            getBreakfastItems();
        });

        btn_filter_brunch.setOnClickListener(view -> {
            Toast.makeText(getContext(), "btn_filter_brunch", Toast.LENGTH_SHORT).show();
        });

        btn_filter_lunch.setOnClickListener(view -> {
            Toast.makeText(getContext(), "btn_filter_lunch", Toast.LENGTH_SHORT).show();
        });

        btn_filter_dinner.setOnClickListener(view -> {
            Toast.makeText(getContext(), "btn_filter_dinner", Toast.LENGTH_SHORT).show();
        });

        bottomSheetDialog.show();
    }

    private void getBreakfastItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Breakfast");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getBrunchItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Brunch");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getLunchItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Lunch");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDinnerItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Dinner");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getSoupItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Soup");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getAppetizerItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Appetizer");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getStarterItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Starter");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getMainDishItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainDish");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getSideItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Side");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDessertItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Dessert");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDrinkItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Brunch");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // list clear
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue().toString().isEmpty()) {
                        Toast.makeText(getContext(), "There is no data !", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // list add(snapshot.getValue().toString());
                    }
                }
                // adapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}