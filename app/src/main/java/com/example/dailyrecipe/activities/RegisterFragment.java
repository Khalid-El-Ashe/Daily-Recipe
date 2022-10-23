package com.example.dailyrecipe.activities;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailyrecipe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {
    private EditText edit_register_fullname, edit_register_email, edit_register_password;
    private Button btn_register_new_account;
    private FirebaseAuth auth;

    private ProgressDialog dialog;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        edit_register_fullname = view.findViewById(R.id.register_full_name);
        edit_register_email = view.findViewById(R.id.register_email_address);
        edit_register_password = view.findViewById(R.id.register_password);
        btn_register_new_account = view.findViewById(R.id.btn_register_register);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("loading register...");
        dialog.setIcon(R.drawable.tick);

        btn_register_new_account.setOnClickListener(view1 -> {
            createNewUserAccount(edit_register_fullname.getText().toString(), edit_register_email.getText().toString(), edit_register_password.getText().toString());
        });

        return view;
    }

    private void createNewUserAccount(String fullName, String email, String password) {
        auth = FirebaseAuth.getInstance();
        fullName = edit_register_fullname.getText().toString();
        email = edit_register_email.getText().toString();
        password = edit_register_password.getText().toString();

        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Something is wrong !", Toast.LENGTH_SHORT).show();
            if (password.length() < 6) {
                Toast.makeText(getContext(), "The password is to short", Toast.LENGTH_SHORT).show();
            }
            return;
        } else {
            dialog.show();
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Success create account", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.layout_container, new HomeFragment()).commit();
                    }
                }
            });
        }
    }
}