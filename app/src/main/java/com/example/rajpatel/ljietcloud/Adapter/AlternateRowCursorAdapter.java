package com.example.rajpatel.ljietcloud.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.example.rajpatel.ljietcloud.R;

/**
 * Created by HimangiPatel on 01/02/16.
 */
public class AlternateRowCursorAdapter extends SimpleCursorAdapter {
    private int[] colors = new int[] { Color.parseColor("#ff77b900"), Color.parseColor("#FF008299"),Color.parseColor("#FFDC572E") };
    //private int[] colors = new int[] { R., 0x30808080 };
    private Cursor c;
    public AlternateRowCursorAdapter(Context context, int layout, Cursor c,
                                     String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.c=c;
    }

    /**
     * Display rows in alternating colors
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);
        return view;
    }

}
