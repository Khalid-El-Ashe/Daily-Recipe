package com.example.dailyrecipe.activities;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.models.Appetizer;
import com.example.dailyrecipe.models.Breakfast;
import com.example.dailyrecipe.models.Brunch;
import com.example.dailyrecipe.models.Dessert;
import com.example.dailyrecipe.models.Dinner;
import com.example.dailyrecipe.models.Drinks;
import com.example.dailyrecipe.models.Lunch;
import com.example.dailyrecipe.models.MainDish;
import com.example.dailyrecipe.models.Side;
import com.example.dailyrecipe.models.Soup;
import com.example.dailyrecipe.models.Starter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddNewItemFragment extends Fragment {
    private ImageView img_save, img_back, img_new_item;

    private EditText edit_new__item_type, edit_new_item_name, edit_new_item_calories, edit_new_item_mins, edit_new_item_serving, edit_new_item_description;

    private Uri imgURI;

    private ActivityResultLauncher<String> launcher;

    private FirebaseStorage storage;
    private FirebaseDatabase database;

    public AddNewItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_item, container, false);

        storage = FirebaseStorage.getInstance();

        img_save = view.findViewById(R.id.img_save_new_item);
        img_back = view.findViewById(R.id.img_add_new_item_back);
        img_new_item = view.findViewById(R.id.img_add_new_item_image);
        edit_new__item_type = view.findViewById(R.id.edit_add_type_item);
        edit_new_item_name = view.findViewById(R.id.edit_add_name_item);
        edit_new_item_calories = view.findViewById(R.id.edit_add_calories_item);
        edit_new_item_mins = view.findViewById(R.id.edit_add_mins_item);
        edit_new_item_serving = view.findViewById(R.id.edit_add_serving_item);
        edit_new_item_description = view.findViewById(R.id.edit_add_description_item);

        img_save.setOnClickListener(view1 -> {
            if (TextUtils.isEmpty(edit_new__item_type.getText().toString()) ||
                    TextUtils.isEmpty(edit_new_item_name.getText().toString()) ||
                    TextUtils.isEmpty(edit_new_item_calories.getText().toString()) ||
                    TextUtils.isEmpty(edit_new_item_mins.getText().toString()) ||
                    TextUtils.isEmpty(edit_new_item_serving.getText().toString()) ||
                    TextUtils.isEmpty(edit_new_item_description.getText().toString())) {

                Toast.makeText(getContext(), "you must to complete all values", Toast.LENGTH_SHORT).show();
                return;
            } else {
                upload();
            }
        });

        img_back.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null).replace(R.id.layout_container, new HomeFragment()).commit();
        });

        img_new_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });


        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    img_new_item.setImageURI(result);
                    imgURI = result;
                }
            }
        });

        return view;
    }

    private void openImage() {
        launcher.launch("image/*");
    }

    private void upload() {
        uploadImage();
    }

    private void uploadImage() {
        if (imgURI != null) {
            // i need to make reference for store the image in firebase storage
            StorageReference reference = storage.getReference().child("images" + UUID.randomUUID().toString());
            reference.putFile(imgURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        uploadItem();
                    }
                }
            });
        }
    }


    private void uploadItem() {

        if (edit_new__item_type.getText().toString().equals("Breakfast") || edit_new__item_type.getText().toString().equals("breakfast")) {
            breakfast();
        } else if (edit_new__item_type.getText().toString().equals("Brunch") || edit_new__item_type.getText().toString().equals("brunch")) {
            brunch();
        } else if (edit_new__item_type.getText().toString().equals("Lunch") || edit_new__item_type.getText().toString().equals("lunch")) {
            lunch();
        } else if (edit_new__item_type.getText().toString().equals("Dinner") || edit_new__item_type.getText().toString().equals("dinner")) {
            dinner();
        } else if (edit_new__item_type.getText().toString().equals("Soup") || edit_new__item_type.getText().toString().equals("soup")) {
            soup();
        } else if (edit_new__item_type.getText().toString().equals("Appetizer") || edit_new__item_type.getText().toString().equals("appetizer")) {
            appetizer();
        } else if (edit_new__item_type.getText().toString().equals("Starter") || edit_new__item_type.getText().toString().equals("starter")) {
            starter();
        } else if (edit_new__item_type.getText().toString().equals("Main Dish") || edit_new__item_type.getText().toString().equals("main dish")) {
            mainDish();
        } else if (edit_new__item_type.getText().toString().equals("Side") || edit_new__item_type.getText().toString().equals("side")) {
            side();
        } else if (edit_new__item_type.getText().toString().equals("Dessert") || edit_new__item_type.getText().toString().equals("dessert")) {
            dessert();
        } else if (edit_new__item_type.getText().toString().equals("Drinks") || edit_new__item_type.getText().toString().equals("drinks")) {
            drink();
        } else {
            Toast.makeText(getContext(), "The type is not correct", Toast.LENGTH_SHORT).show();
        }

        // this is to add one of item and update it
//        database.getReference().child("Breakfast").setValue(breakfast).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void breakfast() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Breakfast breakfast = new Breakfast(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Breakfast").push().setValue(breakfast).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void brunch() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Brunch brunch = new Brunch(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Brunch").push().setValue(brunch).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void lunch() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Lunch lunch = new Lunch(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Lunch").push().setValue(lunch).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dinner() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Dinner dinner = new Dinner(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Dinner").push().setValue(dinner).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void soup() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Soup soup = new Soup(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Soup").push().setValue(soup).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void appetizer() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Appetizer appetizer = new Appetizer(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Appetizer").push().setValue(appetizer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void starter() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Starter starter = new Starter(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Starter").push().setValue(starter).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mainDish() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        MainDish mainDish = new MainDish(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("MainDish").push().setValue(mainDish).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void side() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Side side = new Side(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Side").push().setValue(side).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dessert() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Dessert dessert = new Dessert(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Dessert").push().setValue(dessert).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void drink() {
        String type = edit_new__item_type.getText().toString();
        String name = edit_new_item_name.getText().toString();
        int calories = Integer.parseInt(edit_new_item_calories.getText().toString());
        int mins = Integer.parseInt(edit_new_item_mins.getText().toString());
        int serving = Integer.parseInt(edit_new_item_serving.getText().toString());
        String description = edit_new_item_description.getText().toString();

        Drinks drinks = new Drinks(imgURI.toString(), type, name, calories, mins, serving, description);
        database = FirebaseDatabase.getInstance();
        database.getReference().child("Drinks").push().setValue(drinks).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "can't upload", Toast.LENGTH_SHORT).show();
            }
        });
    }
}