package com.example.rajpatel.ljietcloud.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rajpatel.ljietcloud.Adapter.PagerAdpter_TabView_TimeTable;
import com.example.rajpatel.ljietcloud.R;

/**
 * Created by HimangiPatel on 23/05/16.
 */
public class TimeTableFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_time__table, container, false);

        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdpter_TabView_TimeTable adapter = new PagerAdpter_TabView_TimeTable(getChildFragmentManager());
        adapter.addFragment(new FragmentMonday(), "Monday");
        adapter.addFragment(new Fragment_Tuesday(), "Tuesday");
        adapter.addFragment(new FragmentWednesday(), "Wednesday");
        adapter.addFragment(new FragmentThursday(), "Thursday");
        adapter.addFragment(new FragmentFriday(), "Friday");
        adapter.addFragment(new FragmentSaturday(), "Saturday");
        viewPager.setAdapter(adapter);
    }

}
