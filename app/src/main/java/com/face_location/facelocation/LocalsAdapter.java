package com.face_location.facelocation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by admin on 30.11.17.
 */

public class LocalsAdapter extends ArrayAdapter<ChatUser> {

    private static final String TAG = "LocalsAdapter";
    private Context mContext;
    private int mResource;
    String[] applicationData;


    private static class ViewHolder {
        TextView userName;
        TextView someText;
        ImageView imageView2;
    }


    public LocalsAdapter(Context context, int resource, ArrayList<ChatUser> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String userName = getItem(position).getName();
        String userEmail = getItem(position).getEmail();
        String userAvatar = getItem(position).getAvatar();
        Log.i(TAG, "ССЫЛКА НА АВАТАР: " + userAvatar);

        if (userName == null){
            userName = "Ім'я не вказано";
        }

        if (userName.length() > 25){
            userName = userName.substring(0, Math.min(userName.length(), 25)).trim() + "...";
        }



        try{

            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);

                holder= new ViewHolder();
                holder.userName = (TextView) convertView.findViewById(R.id.userName);
                holder.someText = (TextView) convertView.findViewById(R.id.someText);
                holder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);

                convertView.setTag(holder);

            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.userName.setText(userName);
            holder.someText.setText(userEmail);
            Glide
                    .with(mContext)
                    .load(userAvatar)
                    .thumbnail(0.1f) //shows mini image which weight 0.1 from real image while real image is downloading
                    .apply(RequestOptions
                            .circleCropTransform())
                    .into(holder.imageView2);

            return convertView;

        }catch (IllegalArgumentException e){
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage() );
            return convertView;
        }

    }
}
