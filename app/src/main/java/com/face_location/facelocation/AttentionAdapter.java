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
 * Created by admin on 30.11.17.
 */

public class AttentionAdapter extends ArrayAdapter<Attention> {

    private static final String TAG = "LocalsAdapter";
    private Context mContext;
    private int mResource;


    private static class ViewHolder {
        TextView userName, postTime, attentionTitle, bodyText;
    }


    public AttentionAdapter(Context context, int resource, ArrayList<Attention> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String userName = getItem(position).getUserName();
        if (userName.length() > 25){
            userName = userName.substring(0, Math.min(userName.length(), 25)).trim() + "...";
        }

        String postTime = getItem(position).getTime();

        String attentionTitle = getItem(position).getTitle();
        if (attentionTitle.length() > 25){
            attentionTitle = attentionTitle.substring(0, Math.min(attentionTitle.length(), 25)).trim() + "...";
        }

        String bodyText = getItem(position).getBody();



        try{

            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);

                holder= new ViewHolder();
                holder.userName = (TextView) convertView.findViewById(R.id.userName);
                holder.postTime = (TextView) convertView.findViewById(R.id.someText);
                holder.attentionTitle = (TextView) convertView.findViewById(R.id.attentionTitle);
                holder.bodyText = (TextView) convertView.findViewById(R.id.bodyText);

                convertView.setTag(holder);

            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.userName.setText(userName);
            holder.postTime.setText(postTime);
            holder.attentionTitle.setText(attentionTitle);
            holder.bodyText.setText(bodyText);


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
