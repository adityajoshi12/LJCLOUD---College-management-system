package com.example.rajpatel.ljietcloud.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.ModelClass.NavDrawerItem;
import com.example.rajpatel.ljietcloud.R;

/**
 * Created by Rajpatel on 09/01/16.
 */
public class NavDrawerListAdapter extends ArrayAdapter<NavDrawerItem> {
    Context mContext;
    int layoutResourceId;
    NavDrawerItem data[] = null;

    public NavDrawerListAdapter(Context mContext, int layoutResourceId, NavDrawerItem[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        NavDrawerItem folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}
