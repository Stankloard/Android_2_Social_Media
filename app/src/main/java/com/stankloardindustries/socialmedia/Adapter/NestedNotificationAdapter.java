package com.stankloardindustries.socialmedia.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stankloardindustries.socialmedia.Model.NotificationModel;
import com.stankloardindustries.socialmedia.R;

import java.util.ArrayList;

public class NestedNotificationAdapter extends RecyclerView.Adapter<NestedNotificationAdapter.viewHolder>{

    ArrayList<NotificationModel> list_4;
    Context context;

    public NestedNotificationAdapter(ArrayList<NotificationModel> list_4, Context context) {
        this.list_4 = list_4;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nested_notification_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationModel notificationModel = list_4.get(position);
        holder.nestedProfile.setImageResource(notificationModel.getNestedProfile());
        holder.notification.setText(Html.fromHtml(notificationModel.getNestedNotification()));
        holder.time.setText(notificationModel.getTime());
    }

    @Override
    public int getItemCount() {
        return list_4.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView nestedProfile;
        TextView notification, time;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nestedProfile = itemView.findViewById(R.id.profile_image_nested);
            notification = itemView.findViewById(R.id.notification_nested);
            time = itemView.findViewById(R.id.time);
        }
    }
}
