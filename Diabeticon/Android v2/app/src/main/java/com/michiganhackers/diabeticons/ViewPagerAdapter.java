package com.michiganhackers.diabeticons;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.michiganhackers.diabeticons.Core.FavoriteFragment;
import com.michiganhackers.diabeticons.Core.HomeFragment;
import com.michiganhackers.diabeticons.Core.MoreFragment;
import com.michiganhackers.diabeticons.Core.RecentFragment;

import java.util.ArrayList;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    ArrayList<Fragment> mFragmentList;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new RecentFragment());
        mFragmentList.add(new FavoriteFragment());
        mFragmentList.add(new MoreFragment());
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}