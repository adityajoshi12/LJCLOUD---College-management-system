package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rajpatel.ljietcloud.R;

/**
 * Created by Rajpatel on 27/12/15.
 */
public class Fragment_Schedule_Reminder extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_schedule_reminder,null);
        initView(view);
        return view;
    }

    private void initView(View view){

    }
}
