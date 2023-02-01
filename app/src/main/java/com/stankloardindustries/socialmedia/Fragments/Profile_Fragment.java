package com.stankloardindustries.socialmedia.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.stankloardindustries.socialmedia.Adapter.FriendAdapter;
import com.stankloardindustries.socialmedia.Adapter.PostAdapter;
import com.stankloardindustries.socialmedia.Model.FriendModel;
import com.stankloardindustries.socialmedia.Model.User;
import com.stankloardindustries.socialmedia.R;
import com.stankloardindustries.socialmedia.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class Profile_Fragment extends Fragment {

    RecyclerView rv;
    ArrayList<FriendModel> list;
    FragmentProfileBinding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    public Profile_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                Picasso.get()
                        .load(user.getProfilePhoto())
                        .placeholder(R.drawable.user)
                        .into(binding.profileImage);

                Picasso.get()
                        .load(user.getCoverPhoto())
                        .placeholder(R.drawable.photo)
                        .into(binding.CoverPhoto);

                binding.userName.setText(user.getName());
                binding.profession.setText(user.getProfession());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list = new ArrayList<>();
        list.add(new FriendModel(R.drawable.profile));
        list.add(new FriendModel(R.drawable.profile_1));
        list.add(new FriendModel(R.drawable.profile_2));
        list.add(new FriendModel(R.drawable.profile_3));
        list.add(new FriendModel(R.drawable.profile_4));
        list.add(new FriendModel(R.drawable.profile_5));
        list.add(new FriendModel(R.drawable.profile_6));
        list.add(new FriendModel(R.drawable.profile_7));
        list.add(new FriendModel(R.drawable.profile_8));
        list.add(new FriendModel(R.drawable.profile_9));

        FriendAdapter adapter = new FriendAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.friendRV.setLayoutManager(manager);
        binding.friendRV.setNestedScrollingEnabled(false);
        binding.friendRV.setAdapter(adapter);

        binding.changeCoverPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 11);
            }
        });

        binding.verifiedAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 22);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 11){
            if(data.getData() != null){
                Uri uri = data.getData();
                binding.CoverPhoto.setImageURI(uri);

                final StorageReference reference = storage.getReference().child("Cover_Photo")
                        .child(FirebaseAuth.getInstance().getUid());

                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Cover Photo Updated",Toast.LENGTH_SHORT).show();
                        // copy image from storage to realtime in firebase
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("Users").child(auth.getUid()).child("CoverPhoto").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        }

        if(requestCode == 22){
            if(data.getData() != null){
                Uri uri = data.getData();
                binding.profileImage.setImageURI(uri);

                final StorageReference reference = storage.getReference().child("Profile_Photo")
                        .child(FirebaseAuth.getInstance().getUid());

                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Profile Photo Updated",Toast.LENGTH_SHORT).show();
                        // copy image from storage to realtime in firebase
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("Users").child(auth.getUid()).child("ProfilePhoto").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        }
    }
}