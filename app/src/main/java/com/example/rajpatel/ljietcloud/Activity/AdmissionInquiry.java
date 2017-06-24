package com.example.rajpatel.ljietcloud.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rajpatel.ljietcloud.ModelClass.Admission;
import com.example.rajpatel.ljietcloud.R;
import com.firebase.client.Firebase;

public class AdmissionInquiry extends AppCompatActivity {

    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_inquiry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.color_blue_light));
        }

        final EditText name = (EditText) findViewById(R.id.stud_name);
        final EditText email = (EditText) findViewById(R.id.emailid);
        final EditText phone = (EditText) findViewById(R.id.phone_number);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText query = (EditText) findViewById(R.id.queryMessage);
        final Button submit = (Button) findViewById(R.id.btnSUbmit);
        final Spinner branch = (Spinner) findViewById(R.id.branch);

        firebase = new Firebase("https://ljcloud-android.firebaseio.com/");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if (name.getText()!=null&&email.getText()!=null&&phone.getText()!=null&&city.getText()!=null&&query.getText()!=null){

                  firebase.child("AdmissionInquiry").push().setValue(new Admission(name.getText().toString(), email.getText().toString(), phone.getText().toString(), city.getText().toString(), branch.getSelectedItem().toString(), query.getText().toString()));

                  name.setText("");
                  email.setText("");
                  phone.setText("");
                  city.setText("");
                  query.setText("");
                  branch.getPrompt();

                  Snackbar.make(v,"Admission Form Has been Submitted..",Snackbar.LENGTH_LONG).show();

              }else {

                  Snackbar.make(v,"Fill All the Details...",Snackbar.LENGTH_LONG).show();

              }


            }
        });


    }

}
