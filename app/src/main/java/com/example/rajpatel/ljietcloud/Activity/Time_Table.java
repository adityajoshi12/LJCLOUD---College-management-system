package com.example.rajpatel.ljietcloud.Activity;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.rajpatel.ljietcloud.Adapter.PagerAdpter_TabView_TimeTable;
import com.example.rajpatel.ljietcloud.Fragment.FragmentFriday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentMonday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentSaturday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentThursday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentWednesday;
import com.example.rajpatel.ljietcloud.Fragment.Fragment_Tuesday;
import com.example.rajpatel.ljietcloud.R;

public class Time_Table extends AppCompatActivity {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__table);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Time Table");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.color_blue_light));
        }
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdpter_TabView_TimeTable adapter = new PagerAdpter_TabView_TimeTable(getSupportFragmentManager());
        adapter.addFragment(new FragmentMonday(), "Monday");
        adapter.addFragment(new Fragment_Tuesday(), "Tuesday");
        adapter.addFragment(new FragmentWednesday(), "Wednesday");
        adapter.addFragment(new FragmentThursday(), "Thursday");
        adapter.addFragment(new FragmentFriday(), "Friday");
        adapter.addFragment(new FragmentSaturday(), "Saturday");
        viewPager.setAdapter(adapter);
    }
}

