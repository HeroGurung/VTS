package com.sss.vts.Admin.Org_Page_Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sss.vts.Admin.Org_Fragment.IntroductionFragment;
import com.sss.vts.Admin.Org_Fragment.ObjectiveFragment;
import com.sss.vts.Admin.Org_Fragment.RulesFragment;
import com.sss.vts.Admin.Org_Fragment.StaffsFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int noOfTabs;
    public PageAdapter(FragmentManager fm,int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IntroductionFragment();
            case 1:
                return new ObjectiveFragment();
            case 2:
                return new StaffsFragment();
            case 3:
                return new RulesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}

