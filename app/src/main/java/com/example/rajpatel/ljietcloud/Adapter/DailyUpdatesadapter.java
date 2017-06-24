package com.example.rajpatel.ljietcloud.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rajpatel.ljietcloud.ModelClass.DailyUpdates;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by himangi on 11/05/16.
 */
public class DailyUpdatesadapter extends RecyclerView.Adapter<DailyUpdatesHolder> {

    Context context;
    ArrayList<DailyUpdates> dailyUpdatesArrayList;
    Firebase  firebase;

    public DailyUpdatesadapter(Context context, ArrayList<DailyUpdates> movieArrayList) {
        this.context = context;
        this.dailyUpdatesArrayList = movieArrayList;
        Firebase.setAndroidContext(context);
        firebase = new Firebase("https://ljcloud-android.firebaseio.com/");
    }

    @Override
    public DailyUpdatesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dailyupdates,parent,false);
        DailyUpdatesHolder holder = new DailyUpdatesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DailyUpdatesHolder holder, int position) {

        String name = dailyUpdatesArrayList.get(position).getTitle();
        holder.name.setText(name);
        holder.desc.setText(dailyUpdatesArrayList.get(position).getDescription());

        firebase.child("Students").child(dailyUpdatesArrayList.get(position).getUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                holder.user.setText(dataSnapshot.child("name").getValue(String.class));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return dailyUpdatesArrayList.size();
    }
}
