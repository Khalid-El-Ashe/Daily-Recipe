package com.example.dailyrecipe.activities;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dailyrecipe.R;
import com.example.dailyrecipe.adapters.NotificationsAdapter;
import com.example.dailyrecipe.models.Notification;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView_notifications;
    private ImageView img_back;
    private SearchView searchView_notifications;
    private List<Notification> notificationList;
    private NotificationsAdapter adapter;


    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        img_back = view.findViewById(R.id.img_back_to_home);

        recyclerView_notifications = view.findViewById(R.id.recycler_notifications);
        searchView_notifications = view.findViewById(R.id.search_notifications);

        notificationList = new ArrayList<>();
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));
        notificationList.add(new Notification("27 Des", "Lorem ipsum dolor sit amet, consetetur.", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut."));

        adapter = new NotificationsAdapter(notificationList);
        recyclerView_notifications.setAdapter(adapter);
        recyclerView_notifications.setHasFixedSize(true);
        recyclerView_notifications.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        img_back.setOnClickListener(view1 -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_container, new HomeFragment()).commit();
        });


        return view;
    }
}