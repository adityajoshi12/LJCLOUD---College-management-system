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

import java.util.ArrayList;

/**
 * Created by HimangiPatel on 10/02/16.
 */
public class CourseAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<CourseModel> arrayList;

    public CourseAdapter(Context context, ArrayList<CourseModel> arrayList) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.list_courses, null);
            viewHolder = new ViewHolder();

            viewHolder.courseimage=(ImageView)convertView.findViewById(R.id.courseimage);
            viewHolder.coursename=(TextView)convertView.findViewById(R.id.coursename);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();

        }

        viewHolder.courseimage.setImageResource(arrayList.get(position).getCourseImage());
        viewHolder.coursename.setText(arrayList.get(position).getCourseName());

        return convertView;

    }

    public class ViewHolder{
        public ImageView courseimage;
        public TextView coursename;
    }
}
