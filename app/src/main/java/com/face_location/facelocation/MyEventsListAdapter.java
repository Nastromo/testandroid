package com.face_location.facelocation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 4/4/2017.
 */

public class MyEventsListAdapter extends ArrayAdapter<Event> {

    private static final String TAG = "MyEventsListAdapter";
    private Context mContext;
    private int mResource;


    private static class ViewHolder {
        TextView name, about, status, userQuantity;
    }


    public MyEventsListAdapter(Context context, int resource, ArrayList<Event> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        String name = getItem(position).getName();
        if (name.length() > 29){
            name = name.substring(0, Math.min(name.length(), 29)) + "...";
        }

        String about = getItem(position).getAbour();
        if (about.length() > 100){
            about = about.substring(0, Math.min(about.length(), 100)) + "...";
        }

        String status = getItem(position).getStatus();
        String userQuantity = getItem(position).getUserQuantity();


        try{

            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);

                holder= new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.eventTitle);
                holder.about = (TextView) convertView.findViewById(R.id.aboutEvent);
                holder.status = (TextView) convertView.findViewById(R.id.eventStatus);
                holder.userQuantity = (TextView) convertView.findViewById(R.id.usersQuantity);
                convertView.setTag(holder);

            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(name);
            holder.about.setText(about);
            holder.status.setText(status);
            holder.userQuantity.setText(userQuantity);


            //create the imageloader object
//            ImageLoader imageLoader = ImageLoader.getInstance();
//
//            int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed",null,mContext.getPackageName());
//
//            //create display options
//            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
//                    .cacheOnDisc(true).resetViewBeforeLoading(true)
//                    .showImageForEmptyUri(defaultImage)
//                    .showImageOnFail(defaultImage)
//                    .showImageOnLoading(defaultImage).build();
//
//            //download and display image from url
//            imageLoader.displayImage(imgUrl, holder.image, options);

            return convertView;

        }catch (IllegalArgumentException e){
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage() );
            return convertView;
        }

    }

    /**
     * Required for setting up the Universal Image loader Library
     */
//    private void setupImageLoader(){
//        // UNIVERSAL IMAGE LOADER SETUP
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheOnDisc(true).cacheInMemory(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(300)).build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                mContext)
//                .defaultDisplayImageOptions(defaultOptions)
//                .memoryCache(new WeakMemoryCache())
//                .discCacheSize(100 * 1024 * 1024).build();
//
//        ImageLoader.getInstance().init(config);
//        // END - UNIVERSAL IMAGE LOADER SETUP
//    }
}
