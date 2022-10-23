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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dailyrecipe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignFragment extends Fragment {
    private EditText edit_sign_email, edit_sign_password;
    private LinearLayout layout_new_account;
    private Button btn_sign_account;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog dialog;

    public SignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign, container, false);

        edit_sign_email = view.findViewById(R.id.sign_email_address);
        edit_sign_password = view.findViewById(R.id.sign_password);
        btn_sign_account = view.findViewById(R.id.btn_sign);
        layout_new_account = view.findViewById(R.id.layout_new_account);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("loading sign...");
        dialog.setIcon(R.drawable.tick);

        btn_sign_account.setOnClickListener(view1 -> {
            signUserAccount(edit_sign_email.getText().toString(), edit_sign_password.getText().toString());
        });

        layout_new_account.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.layout_container, new RegisterFragment()).commit();
        });

        return view;
    }

    private void signUserAccount(String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();
        email = edit_sign_email.getText().toString();
        password = edit_sign_password.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Something is wrong !", Toast.LENGTH_SHORT).show();
            return;
        } else {
            dialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "sign in success", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.layout_container, new HomeFragment()).commit();
                }
            });
        }
    }
}