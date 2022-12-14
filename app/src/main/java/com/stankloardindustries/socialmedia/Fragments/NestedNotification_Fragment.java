package com.stankloardindustries.socialmedia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stankloardindustries.socialmedia.Adapter.NestedNotificationAdapter;
import com.stankloardindustries.socialmedia.Adapter.PostAdapter;
import com.stankloardindustries.socialmedia.Model.NotificationModel;
import com.stankloardindustries.socialmedia.R;

import java.util.ArrayList;

public class NestedNotification_Fragment extends Fragment {

    RecyclerView rvNotification;
    ArrayList<NotificationModel> list;

    public NestedNotification_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nested_notification_, container, false);

        rvNotification = view.findViewById(R.id.notificationRecyclerView);

        list = new ArrayList<>();

        list.add(new NotificationModel(R.drawable.photo_29, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_30, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_31, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_32, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_33, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_34, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_35, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_36, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_37, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_38, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_39, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_41, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_42, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_43, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_44, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_45, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_46, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_47, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_48, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.photo_49, getContext().getString(R.string.notn), "Just Now"));

        NestedNotificationAdapter adapter_3 = new NestedNotificationAdapter(list, getContext());
        LinearLayoutManager manager_2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvNotification.setLayoutManager(manager_2);
        rvNotification.setNestedScrollingEnabled(false);
        rvNotification.setAdapter(adapter_3);

        return view;
    }
}