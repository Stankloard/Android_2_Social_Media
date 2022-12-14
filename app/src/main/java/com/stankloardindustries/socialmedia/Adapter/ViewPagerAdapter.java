package com.stankloardindustries.socialmedia.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.stankloardindustries.socialmedia.Fragments.NestedNotification_Fragment;
import com.stankloardindustries.socialmedia.Fragments.Request_Fragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new NestedNotification_Fragment();
            case 1: return new Request_Fragment();
            default: return new NestedNotification_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;
        if(position == 0){
            title = "NOTIFICATION";
        }
        else if(position == 1){
            title = "REQUEST";
        }

        return title;
    }
}
