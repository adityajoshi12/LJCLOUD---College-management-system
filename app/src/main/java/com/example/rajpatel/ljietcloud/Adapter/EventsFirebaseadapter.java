package com.example.rajpatel.ljietcloud.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.ModelClass.EventsFirebasemodel;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by himangi on 08/04/16.
 */
public class EventsFirebaseadapter extends FirebaseListAdapter<EventsFirebasemodel> {

    private Context mContext;

    public EventsFirebaseadapter(Activity activity, Class<EventsFirebasemodel> modelClass, int modelLayout, Query ref, Context mContext) {
        super(activity, modelClass, modelLayout, ref);
        this.mContext = mContext;
    }


    @Override
    protected void populateView(View view, EventsFirebasemodel eventFirebase) {

        String updateMsg = eventFirebase.getEventName();
        String imgURL = eventFirebase.getEventIMG();

        TextView updates = (TextView) view.findViewById(R.id.updates);
        ImageView cover= (ImageView) view.findViewById(R.id.imgCover);

        updates.setText(updateMsg);
        Picasso.with(mContext).load(imgURL).into(cover);

    }


}
