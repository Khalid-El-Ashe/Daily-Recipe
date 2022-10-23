package com.example.dailyrecipe.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailyrecipe.R;

public class SettingFragment extends Fragment {
    private TextView text_language;
    private Switch switch_notification, switch_email;

    private final boolean check_n = true;
    private final boolean check_e = true;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        text_language = view.findViewById(R.id.text_language);
        switch_notification = view.findViewById(R.id.switch_notification);
        switch_email = view.findViewById(R.id.switch_email);

        text_language.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "changed", Toast.LENGTH_SHORT).show();
        });

        switch_notification.setOnClickListener(view1 -> {
            if (check_n == true) {
                Toast.makeText(getContext(), "the notification is enable", Toast.LENGTH_SHORT).show();
                return;
            } else if (check_n == false) {
                Toast.makeText(getContext(), "the notification is disable", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        switch_email.setOnClickListener(view1 -> {
            if (check_e == true) {
                Toast.makeText(getContext(), "the email is enable", Toast.LENGTH_SHORT).show();
                return;
            } else if (check_e == false) {
                Toast.makeText(getContext(), "the email is disable", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        return view;
    }
}