package com.example.rajpatel.ljietcloud.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rajpatel.ljietcloud.Adapter.CourseAdapter;
import com.example.rajpatel.ljietcloud.Adapter.CourseModel;
import com.example.rajpatel.ljietcloud.DatabaseFile.DatabaseHelper;
import com.example.rajpatel.ljietcloud.Fragment.DialogCourses;
import com.example.rajpatel.ljietcloud.Fragment.Dialogfragmentclass;
import com.example.rajpatel.ljietcloud.R;

import java.util.ArrayList;

public class CoursesinLj extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursesin_lj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Course at LJ");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        final ListView listView=(ListView)findViewById(R.id.listview_courses);

        if (Build.VERSION.SDK_INT >= 21)  {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.color_blue_light));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final ArrayList<CourseModel> dataArrayList=new ArrayList<CourseModel>();
        dataArrayList.add(new CourseModel("Automobile Engineering",R.drawable.automobile));
        dataArrayList.add(new CourseModel("Chemical Engineering",R.drawable.chemicaleng));
        dataArrayList.add(new CourseModel("Civil Engineering",R.drawable.student));
        dataArrayList.add(new CourseModel("Computer Engineeing",R.drawable.automobile));
        dataArrayList.add(new CourseModel("Electronics Communication Engineering",R.drawable.automobile));
        dataArrayList.add(new CourseModel("Instrumentation Contol Engineering",R.drawable.automobile));
        dataArrayList.add(new CourseModel("Information Communication Technology",R.drawable.automobile));
        dataArrayList.add(new CourseModel("Information Technology",R.drawable.automobile));
        dataArrayList.add(new CourseModel("Mechanical Engineering",R.drawable.automobile));

        final CourseAdapter courseAdapter=new CourseAdapter(CoursesinLj.this,dataArrayList);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                DialogCourses dialogCourses=new DialogCourses(position);
                dialogCourses.show(fragmentManager, "show dialog");
            }
        });
    }

}
