package com.example.rajpatel.ljietcloud.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rajpatel.ljietcloud.Fragment.FragmentEvents;
import com.example.rajpatel.ljietcloud.Fragment.FragmentLibrary;
import com.example.rajpatel.ljietcloud.Fragment.FragmentNoticeBoard;
import com.example.rajpatel.ljietcloud.Fragment.SuggestionBoxFragment;

/**
 * Created by himangi on 20/03/16.
 */
public class PagerAdpter_TabView_Colegecorner extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdpter_TabView_Colegecorner(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentNoticeBoard tab = new FragmentNoticeBoard();
                return tab;
            case 1:
                FragmentLibrary tab1 = new FragmentLibrary();
                return tab1;
            case 2:
               return new FragmentEvents();
            case 3:
                FragmentLibrary tab3 = new FragmentLibrary();
                return tab3;
            case 4:
               return new SuggestionBoxFragment();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
