package com.example.rajpatel.ljietcloud.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rajpatel.ljietcloud.Activity.Time_Table;
import com.example.rajpatel.ljietcloud.Fragment.FragmentFriday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentMonday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentMonday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentSaturday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentThursday;
import com.example.rajpatel.ljietcloud.Fragment.FragmentWednesday;
import com.example.rajpatel.ljietcloud.Fragment.Fragment_Tuesday;
import com.example.rajpatel.ljietcloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajpatel on 27/12/15.
 */
public class PagerAdpter_TabView_TimeTable extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public PagerAdpter_TabView_TimeTable(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentMonday tab1 = new FragmentMonday();
                return tab1;
            case 1:
                Fragment_Tuesday tab2 = new Fragment_Tuesday();
                return tab2;
            case 2:
                FragmentWednesday tab3 = new FragmentWednesday();
                return tab3;
            case 3:
                FragmentThursday tab4 = new FragmentThursday();
                return tab4;
            case 4:
                FragmentFriday tab5 = new FragmentFriday();
                return tab5;
            case 5:
                FragmentSaturday tab6 = new FragmentSaturday();
                return tab6;
            default:
                return null;
        }
    }
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
