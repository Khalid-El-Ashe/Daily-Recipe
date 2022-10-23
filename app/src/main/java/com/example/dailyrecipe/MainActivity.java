package com.example.dailyrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.dailyrecipe.activities.HomeFragment;
import com.example.dailyrecipe.activities.SplashFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.layout_container);

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new SplashFragment()).commit();
    }
}