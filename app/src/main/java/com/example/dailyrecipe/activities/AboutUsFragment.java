package com.example.dailyrecipe.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dailyrecipe.R;

public class AboutUsFragment extends Fragment {
    private TextView facebookLink, instagramLink, githubLink;

    private final String facebook = "https://www.facebook.com/profile.php";
    private final String instagram = "https://www.instagram.com/khalidalashi02";
    private final String github = "https://github.com/Khalid-El-Ashe";

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        facebookLink = view.findViewById(R.id.dev_facebook);
        instagramLink = view.findViewById(R.id.dev_instagram);
        githubLink = view.findViewById(R.id.dev_github);

        facebookLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(facebook);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        instagramLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(instagram);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(github);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        return view;
    }
}