package com.stankloardindustries.socialmedia.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
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
import com.stankloardindustries.socialmedia.Adapter.FollowAdapter;
import com.stankloardindustries.socialmedia.Model.FollowModel;
import com.stankloardindustries.socialmedia.Model.User;
import com.stankloardindustries.socialmedia.R;
import com.stankloardindustries.socialmedia.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class Profile_Fragment extends Fragment {

    RecyclerView rv;
    ArrayList<FollowModel> list;
    FragmentProfileBinding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog dialogProfile;
    ProgressDialog dialogCover;

    public Profile_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        dialogProfile = new ProgressDialog(getContext());
        dialogCover = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        dialogProfile.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogProfile.setMessage("Please Wait ...");
        dialogProfile.setTitle("Profile Uploading");
        dialogProfile.setCancelable(false);
        dialogProfile.setCanceledOnTouchOutside(false);

        dialogCover.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogCover.setMessage("Please Wait ...");
        dialogCover.setTitle("Cover Photot Uploading");
        dialogCover.setCancelable(false);
        dialogCover.setCanceledOnTouchOutside(false);


        database.getReference().child("Users").child(auth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
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

                    binding.txtFollowers.setText(user.getFollowerCount() + " ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list = new ArrayList<>();
        //list.add(new FollowModel(R.drawable.profile));

        FollowAdapter adapter = new FollowAdapter(list, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.friendRV.setLayoutManager(manager);
        binding.friendRV.setNestedScrollingEnabled(false);
        binding.friendRV.setAdapter(adapter);

        database.getReference().child("Users")
                        .child(auth.getUid())
                        .child("Followers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot ss: snapshot.getChildren()){
                            FollowModel followModel = ss.getValue(FollowModel.class);
                            list.add(followModel);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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

        // Cover Photo
        if(requestCode == 11){
            dialogCover.show();
            if(data.getData() != null){
                Uri uri = data.getData();
                binding.CoverPhoto.setImageURI(uri);

                final StorageReference reference = storage.getReference().child("coverPhoto")
                        .child(FirebaseAuth.getInstance().getUid());

                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialogCover.dismiss();
                        Toast.makeText(getContext(), "Cover Photo Updated",Toast.LENGTH_SHORT).show();
                        // copy image from storage to realtime in firebase
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("Users").child(auth.getUid()).child("coverPhoto").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        }

        // Profile Photo
        if(requestCode == 22){
            dialogProfile.show();
            if(data.getData() != null){
                Uri uri = data.getData();
                binding.profileImage.setImageURI(uri);

                final StorageReference reference = storage.getReference().child("profilePhoto")
                        .child(FirebaseAuth.getInstance().getUid());

                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialogProfile.dismiss();
                        Toast.makeText(getContext(), "Profile Photo Updated",Toast.LENGTH_SHORT).show();
                        // copy image from storage to realtime in firebase
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("Users").child(auth.getUid()).child("profilePhoto").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        }
    }
}