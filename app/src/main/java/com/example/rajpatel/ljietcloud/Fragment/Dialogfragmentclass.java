package com.example.rajpatel.ljietcloud.Fragment;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.rajpatel.ljietcloud.DatabaseFile.SQLController;
import com.example.rajpatel.ljietcloud.R;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by HimangiPatel on 01/02/16.
 */
public class Dialogfragmentclass extends android.support.v4.app.DialogFragment {

    SQLController dbcon;
    private EditText lec_time;
    private EditText lec_name;
    public static String lectime;
    public static String lecname;
    private String TABLE_NAME;



    public Dialogfragmentclass(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbcon = new SQLController(getActivity());
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogtimetable, container, false);
        getDialog().setTitle("Time Table Schedule");
        getDialog().setCancelable(false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        lec_time = (EditText) view.findViewById(R.id.lectime);
        lec_name = (EditText) view.findViewById(R.id.lec_name);
        final Button save = (Button) view.findViewById(R.id.save);

        lec_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                final int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                final int mMinite = calendar.get(Calendar.MINUTE);
                final int mSec = calendar.get(Calendar.SECOND);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.app.AlertDialog.THEME_TRADITIONAL, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int Hour = hourOfDay;
                        int Minite = minute;
                        lec_time.setText(Hour + ":" + Minite);
                        Log.d("Time", "Time: " + lec_time.getText().toString());
                    }
                }, mHour, mMinite, true);

                timePickerDialog.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()) {
                    case R.id.save:
                        lectime = lec_time.getText().toString();
                        lecname = lec_name.getText().toString();
                        dbcon.insertData(lectime,lecname, TABLE_NAME);
                        getTargetFragment().onActivityResult(0, Activity.RESULT_OK,new Intent());
                        Dialogfragmentclass.this.dismissAllowingStateLoss();
                        break;

                    case R.id.cancel:
                        dismiss();
                        break;
                }
                dismiss();
            }
        });
    }
}
