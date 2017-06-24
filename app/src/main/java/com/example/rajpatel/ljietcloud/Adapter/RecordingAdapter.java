package com.example.rajpatel.ljietcloud.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajpatel.ljietcloud.ModelClass.ClassRecording;
import com.example.rajpatel.ljietcloud.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by HimangiPatel on 23/05/16.
 */
public class RecordingAdapter  extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<ClassRecording> arrayList;

    public RecordingAdapter(Context context, List<ClassRecording> arrayList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.list_added_sublist, null);
            viewHolder = new ViewHolder();

            viewHolder.sunID = (TextView) convertView.findViewById(R.id.sub_id);
            viewHolder.subName = (TextView) convertView.findViewById(R.id.subject_name);
            viewHolder.subDeatails = (TextView) convertView.findViewById(R.id.sub_notes);
            viewHolder.play = (ImageView) convertView.findViewById(R.id.play_record);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();

        }

        viewHolder.sunID.setText(arrayList.get(position).getId());
        viewHolder.subName.setText(arrayList.get(position).getSUBJECT());
        viewHolder.subDeatails.setText(arrayList.get(position).getSUB_NOTES());
        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer m = new MediaPlayer();
                try {
                    m.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + arrayList.get(position).getSUB_RECORDPATH());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                m.start();

            }
        });


        return convertView;

    }

    public class ViewHolder{

        TextView sunID;
        TextView subName;
        TextView subDeatails;
        ImageView play;
    }

}
