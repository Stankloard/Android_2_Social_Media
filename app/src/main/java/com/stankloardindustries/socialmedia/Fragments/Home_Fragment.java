package com.stankloardindustries.socialmedia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stankloardindustries.socialmedia.Adapter.PostAdapter;
import com.stankloardindustries.socialmedia.Adapter.StoryAdapter;
import com.stankloardindustries.socialmedia.Model.PostModel;
import com.stankloardindustries.socialmedia.Model.StoryModel;
import com.stankloardindustries.socialmedia.R;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {

    RecyclerView storyRV, dashboardRV;
    ArrayList<StoryModel> list;
    ArrayList<PostModel> list_2;
    ImageView addStory;
    FirebaseDatabase database;
    FirebaseAuth auth;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        storyRV = view.findViewById(R.id.storyRV);
        list = new ArrayList<>();

        list.add(new StoryModel(R.drawable.story_1, R.drawable.ic_live, R.drawable.profile_5, "Emma"));
        list.add(new StoryModel(R.drawable.story_2, R.drawable.ic_video, R.drawable.profile_6, "Alexander"));
        list.add(new StoryModel(R.drawable.sotry_3, R.drawable.ic_video, R.drawable.profile_7, "Priya"));
        list.add(new StoryModel(R.drawable.story_4, R.drawable.ic_live, R.drawable.profile_8, "Neha"));

        StoryAdapter adapter = new StoryAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        storyRV.setLayoutManager(manager);
        storyRV.setNestedScrollingEnabled(false);
        storyRV.setAdapter(adapter);

        dashboardRV = view.findViewById(R.id.dashboardRV);
        list_2 = new ArrayList<>();

        PostAdapter adapter_2 = new PostAdapter(list_2, getContext());
        LinearLayoutManager manager_2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dashboardRV.setLayoutManager(manager_2);
        dashboardRV.setNestedScrollingEnabled(false);
        dashboardRV.setAdapter(adapter_2);

        database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list_2.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PostModel post = dataSnapshot.getValue(PostModel.class);
                    post.setPostId(dataSnapshot.getKey());
                    list_2.add(post);
                }
                adapter_2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addStory = view.findViewById(R.id.addStory);
        addStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}