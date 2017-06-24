package com.example.rajpatel.ljietcloud.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.rajpatel.ljietcloud.Adapter.GridMyprofileAdapter;
import com.example.rajpatel.ljietcloud.R;


public class FragmentmyProfiile extends android.support.v4.app.Fragment {

    int[] logo = {R.mipmap.dailydiary, R.mipmap.timetable, R.mipmap.icard, R.mipmap.timetable, R.mipmap.timetable};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragmentmyprofiile, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {

        final GridView gridView = (GridView) view.findViewById(R.id.grid_myprofie);
        gridView.setAdapter(new GridMyprofileAdapter(getActivity().getResources().getStringArray(R.array.myprofile_grid_item_title), logo, getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new TimeTableFragment()).addToBackStack("himangi").commit();

                } else if (position == 0) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new FragmentClassRecords()).addToBackStack("himangi").commit();

                } else if (position == 2) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, new FragmentIDcard()).commit();

                }

            }
        });

    }
}
