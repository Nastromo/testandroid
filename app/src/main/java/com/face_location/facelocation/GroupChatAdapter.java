package com.face_location.facelocation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.face_location.facelocation.model.GetEvent.User;

import java.util.ArrayList;

/**
 * Created by admin on 18.01.18.
 */

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {
    ArrayList<User> localizedUsers;
    Context mContext;

    public GroupChatAdapter(Context mContext, ArrayList<User> localizedUsers) {
        this.mContext = mContext;
        this.localizedUsers = localizedUsers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locals_group_card, parent, false);
        return new GroupChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.userName.setText(localizedUsers.get(position).getUsername());
        holder.someText.setText(localizedUsers.get(position).getEmail());
        Glide
                .with(mContext)
                .load(localizedUsers.get(position).getAvatarMob())
                .thumbnail(0.1f)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imageView2);
    }

    @Override
    public int getItemCount() {
        return localizedUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        TextView userName, someText;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            userName = (TextView) itemView.findViewById(R.id.userName);
            someText = (TextView) itemView.findViewById(R.id.someText);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
