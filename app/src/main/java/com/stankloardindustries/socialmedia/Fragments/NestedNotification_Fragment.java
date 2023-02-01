package com.stankloardindustries.socialmedia.Fragments;

import android.os.Bundle;

import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
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

        list.add(new NotificationModel(R.drawable.profile_1, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_2, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_3, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_4, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_5, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_6, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_7, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_8, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_9, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_10,getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_1, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_2, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_3, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_4, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_5, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_6, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_7, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_8, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_9, getContext().getString(R.string.notn), "Just Now"));
        list.add(new NotificationModel(R.drawable.profile_10,getContext().getString(R.string.notn), "Just Now"));

        NestedNotificationAdapter adapter_3 = new NestedNotificationAdapter(list, getContext());
        LinearLayoutManager manager_2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvNotification.setLayoutManager(manager_2);
        rvNotification.setNestedScrollingEnabled(false);
        rvNotification.setAdapter(adapter_3);

        return view;
    }
}