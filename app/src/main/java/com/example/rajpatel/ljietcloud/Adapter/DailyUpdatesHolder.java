package com.example.rajpatel.ljietcloud.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.R;

/**
 * Created by himangi on 11/05/16.
 */
public class DailyUpdatesHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView desc;
    TextView user;

    public DailyUpdatesHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.title);
        desc = (TextView) itemView.findViewById(R.id.description);
        user = (TextView) itemView.findViewById(R.id.user);

    }
}
