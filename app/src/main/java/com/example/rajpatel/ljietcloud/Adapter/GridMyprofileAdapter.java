package com.example.rajpatel.ljietcloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.R;

/**
 * Created by himangi patel on 11/01/16.
 */
public class GridMyprofileAdapter extends BaseAdapter {
    private static String[] text_title;
    private static int[] logo;
    Context context;
    private static LayoutInflater inflater = null;

    public GridMyprofileAdapter(String[] text_title, int[] logo,Context context) {

        this.text_title = text_title;
        this.context = context;
        this.logo=logo;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return text_title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();


        convertView = inflater.inflate(R.layout.grid_item_myprofile, null);
        viewHolder.text_title = (TextView) convertView.findViewById(R.id.title);
        viewHolder.logo = (ImageView) convertView.findViewById(R.id.logo);
        viewHolder.grid_layout=(LinearLayout)convertView.findViewById(R.id.grid_view);

        viewHolder.text_title.setText(text_title[position]);
        viewHolder.logo.setImageResource(logo[position]);

        if (position==0){
            viewHolder.grid_layout.setBackgroundResource(R.color.color_orange);
        }else if (position==1){
            viewHolder.grid_layout.setBackgroundResource(R.color.color_blue_dark);
        }else if (position==2){
            viewHolder.grid_layout.setBackgroundResource(R.color.color_green_dark);
        }else if (position==3){
            viewHolder.grid_layout.setBackgroundResource(R.color.color_green_light);
        }else if (position==4){
            viewHolder.grid_layout.setBackgroundResource(R.color.color_blue_light);
        }

        return convertView;
    }

    public class ViewHolder {

        public TextView text_title;
        public ImageView logo;
        private LinearLayout grid_layout;
    }
}
