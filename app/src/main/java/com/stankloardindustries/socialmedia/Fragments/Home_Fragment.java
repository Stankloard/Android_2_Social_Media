package com.stankloardindustries.socialmedia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        list_2.add(new PostModel(R.drawable.profile_1, R.drawable.post_1, R.drawable.ic_bmark, "Supriya", "Foodie", "200", "20", "40"));
        list_2.add(new PostModel(R.drawable.profile_2, R.drawable.post_2, R.drawable.ic_bmark, "Bhumika", "Traveller", "400", "55", "90"));
        list_2.add(new PostModel(R.drawable.profile_3, R.drawable.post_3, R.drawable.ic_bmark, "Nina", "Entrepreneur", "267", "16", "45"));
        list_2.add(new PostModel(R.drawable.profile_4, R.drawable.post_4, R.drawable.ic_bmark, "Alisa", "Singer", "345", "55", "87"));
        list_2.add(new PostModel(R.drawable.profile_5, R.drawable.post_5, R.drawable.ic_bmark, "Sofia", "Dancer", "237", "25", "69"));
        list_2.add(new PostModel(R.drawable.profile_6, R.drawable.post_6, R.drawable.ic_bmark, "Sara", "Gamer", "745", "89", "110"));
        list_2.add(new PostModel(R.drawable.profile_7, R.drawable.post_7, R.drawable.ic_bmark, "Diana", "Youtuber", "982", "189", "342"));

        PostAdapter adapter_2 = new PostAdapter(list_2, getContext());
        LinearLayoutManager manager_2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dashboardRV.setLayoutManager(manager_2);
        dashboardRV.setNestedScrollingEnabled(false);
        dashboardRV.setAdapter(adapter_2);

        return view;
    }
}