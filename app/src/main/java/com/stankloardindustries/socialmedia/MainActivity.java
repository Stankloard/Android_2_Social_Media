package com.stankloardindustries.socialmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.iammert.library.readablebottombar.ReadableBottomBar;
import com.stankloardindustries.socialmedia.Fragments.Add_Fragment;
import com.stankloardindustries.socialmedia.Fragments.Home_Fragment;
import com.stankloardindustries.socialmedia.Fragments.Notificatiion_Fragment;
import com.stankloardindustries.socialmedia.Fragments.Profile_Fragment;
import com.stankloardindustries.socialmedia.Fragments.Search_Fragment;
import com.stankloardindustries.socialmedia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        MainActivity.this.setTitle("My Profile");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        binding.toolbar.setVisibility(View.GONE);
        transaction.replace(R.id.container, new Home_Fragment());
        transaction.commit();

        binding.readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (i){
                    case 0:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.container, new Home_Fragment());
                        transaction.commit();
                        break;
                    case 1:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.container, new Notificatiion_Fragment());
                        transaction.commit();
                        break;
                    case 2:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.container, new Add_Fragment());
                        transaction.commit();
                        break;
                    case 3:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.container, new Search_Fragment());
                        transaction.commit();
                        break;
                    case 4:
                        binding.toolbar.setVisibility(View.VISIBLE);
                        transaction.replace(R.id.container, new Profile_Fragment());
                        transaction.commit();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }
}