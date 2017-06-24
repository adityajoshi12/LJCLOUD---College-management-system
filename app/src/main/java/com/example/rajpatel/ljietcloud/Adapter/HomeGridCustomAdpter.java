package com.example.rajpatel.ljietcloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.R;

/**
 * Created by Rajpatel on 04/11/15.
 */
public class HomeGridCustomAdpter extends BaseAdapter {

    private static String[] item;
    private static Integer[] item_image;
    Context context;
    private static LayoutInflater inflater = null;

    public HomeGridCustomAdpter(String[] item, Integer[] imageuser_login, Context context) {

        this.item = item;
        this.item_image = imageuser_login;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return item.length;
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


        convertView = inflater.inflate(R.layout.logingrid_items, null);

        viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.imagegrid);
        viewHolder.textView = (TextView) convertView.findViewById(R.id.itemtext);

        viewHolder.textView.setText(item[position]);
        viewHolder.imageView1.setImageResource(item_image[position]);

        return convertView;
    }

    public class ViewHolder {

        public ImageView imageView1;
        public TextView textView;
    }

}
