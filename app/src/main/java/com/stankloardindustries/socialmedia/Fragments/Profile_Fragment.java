package com.stankloardindustries.socialmedia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stankloardindustries.socialmedia.Adapter.FriendAdapter;
import com.stankloardindustries.socialmedia.Adapter.PostAdapter;
import com.stankloardindustries.socialmedia.Model.FriendModel;
import com.stankloardindustries.socialmedia.R;

import java.util.ArrayList;

public class Profile_Fragment extends Fragment {

    RecyclerView rv;
    ArrayList<FriendModel> list;

    public Profile_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        rv = view.findViewById(R.id.friendRV);

        list = new ArrayList<>();
        list.add(new FriendModel(R.drawable.photo_16));
        list.add(new FriendModel(R.drawable.photo_17));
        list.add(new FriendModel(R.drawable.photo_18));
        list.add(new FriendModel(R.drawable.photo_19));
        list.add(new FriendModel(R.drawable.photo_20));
        list.add(new FriendModel(R.drawable.photo_21));
        list.add(new FriendModel(R.drawable.photo_22));
        list.add(new FriendModel(R.drawable.photo_23));
        list.add(new FriendModel(R.drawable.photo_24));
        list.add(new FriendModel(R.drawable.photo_25));
        list.add(new FriendModel(R.drawable.photo_26));
        list.add(new FriendModel(R.drawable.photo_27));
        list.add(new FriendModel(R.drawable.photo_28));
        list.add(new FriendModel(R.drawable.photo_29));

        FriendAdapter adapter = new FriendAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(adapter);

        return view;
    }
}