package com.example.rajpatel.ljietcloud.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.R;

import java.util.ArrayList;

/**
 * Created by Rajpatel on 27/12/15.
 */
public class Create_Lecture_List_Adapter extends BaseAdapter {

    Context context;
    private ArrayList<String> arrayList;
    private LayoutInflater layoutInflater;

    public Create_Lecture_List_Adapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
      if(convertView==null){

           viewHolder=new ViewHolder();


          convertView=layoutInflater.inflate(R.layout.create_day_list_item, null);


          viewHolder.txt_sub= (TextView) convertView.findViewById(R.id.sub_name);
          viewHolder.delet_btn=(Button)convertView.findViewById(R.id.delet_sub_btn);
          viewHolder.opn_btn=(Button)convertView.findViewById(R.id.open_sub_btn);

          convertView.setTag(viewHolder);

          final String name=arrayList.get(position).toString();
          viewHolder.txt_sub.setText(name);

          viewHolder.delet_btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  arrayList.remove(arrayList.get(position));
                  notifyDataSetChanged();
              }
          });



      }
      else {
          viewHolder =(ViewHolder)convertView.getTag();
      }

        return convertView;
    }

    public class ViewHolder {

        public TextView txt_sub;
        public Button delet_btn;
        public Button opn_btn;
    }

    public void addData(final String name){
        arrayList.add(name);
        notifyDataSetChanged();

    }
}
