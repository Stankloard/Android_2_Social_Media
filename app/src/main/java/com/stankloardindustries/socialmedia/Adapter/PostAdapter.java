package com.stankloardindustries.socialmedia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stankloardindustries.socialmedia.Model.PostModel;
import com.stankloardindustries.socialmedia.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder>{

    ArrayList<PostModel> list;
    Context context;

    public PostAdapter(ArrayList<PostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout._dashboard_rv_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostModel model = list.get(position);
        holder.profile.setImageResource(model.getProfile());
        holder.postImg.setImageResource(model.getPostImg());
        holder.saveImg.setImageResource(model.getSaveImg());
        holder.name.setText(model.getName());
        holder.about.setText(model.getAbout());
        holder.like.setText(model.getLike());
        holder.comment.setText(model.getComment());
        holder.share.setText(model.getShare());

        final boolean[] like_condition = {true};

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(like_condition[0]){
                    holder.like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_red,0,0,0);
                    like_condition[0] = false;
                }
                else{
                    holder.like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like,0,0,0);
                    like_condition[0] = true;
                }
            }
        });

        final boolean[] bk_condition = {true};

        holder.saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bk_condition[0]){
                    holder.saveImg.setImageResource(R.drawable.ic_bkmrk_green);
                    bk_condition[0] = false;
                }
                else{
                    holder.saveImg.setImageResource(R.drawable.ic_bmark);
                    bk_condition[0] = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile, postImg, saveImg;
        TextView name, about, like, comment, share;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_image_2);
            postImg = itemView.findViewById(R.id.postImage);
            saveImg = itemView.findViewById(R.id.save);
            name = itemView.findViewById(R.id.username);
            about = itemView.findViewById(R.id.about);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            share = itemView.findViewById(R.id.forward);
        }
    }
}
