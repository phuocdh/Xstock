package com.xstock.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.xstock.activity.FragmentTradeImageDay;
import com.xstock.activity.FragmentTradeImageHour;

public class TabPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[];

    public TabPagerAdapter(android.support.v4.app.FragmentManager fm, String tabTitle[]) {
        super(fm);
        this.tabTitles = tabTitle;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentTradeImageDay();
            case 1:
                return new FragmentTradeImageHour();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}