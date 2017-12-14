package com.face_location.facelocation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView eventTitle;
        public TextView eventDate;
        public TextView passType;
        public TextView userQuantity;

        public ImageView mainPic;
        public ImageView userPic;
        public ImageView userPicSecond;
        public ImageView userPicThird;


        public ViewHolder(final View itemView) {
            super(itemView);
            eventTitle = (TextView) itemView.findViewById(R.id.eventTitle);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
//            passType = (TextView) itemView.findViewById(R.id.passType);
//            userQuantity = (TextView) itemView.findViewById(R.id.userQuantity);

//            mainPic = (ImageView) itemView.findViewById(R.id.mainPic);
//            userPic = (ImageView) itemView.findViewById(R.id.userPic);
//            userPicSecond = (ImageView) itemView.findViewById(R.id.userPicSecond);
//            userPicThird = (ImageView) itemView.findViewById(R.id.userPicThird);
        }

    }
    private List<SimilarEvent> similarEventList;

    public RecyclerViewAdapter(List<SimilarEvent> events) {
        similarEventList = events;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.similar_event_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        SimilarEvent events = similarEventList.get(position);

        TextView eventTitle = viewHolder.eventTitle;
        eventTitle.setText(events.getEventTitle());

        TextView eventDate = viewHolder.eventDate;
        eventDate.setText(events.getEventDate());
    }

    @Override
    public int getItemCount() {
        return similarEventList.size();
    }
}