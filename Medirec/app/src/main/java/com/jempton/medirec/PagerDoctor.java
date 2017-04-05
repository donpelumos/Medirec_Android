package com.jempton.medirec;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by BALE on 05/04/2017.
 */

public class PagerDoctor extends FragmentStatePagerAdapter {
    int tabCount = 0;
    public PagerDoctor(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                FragmentTab1Doctor tab1 = new FragmentTab1Doctor();
                return tab1;
            case 1:
                FragmentTab2Doctor tab2 = new FragmentTab2Doctor();
                return tab2;
            case 2:
                FragmentTab3Doctor tab3 = new FragmentTab3Doctor();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return  tabCount;
    }
}