package com.example.oslotest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public FragmentPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                MyPageFragment myPageFragment = new MyPageFragment();
                return  myPageFragment;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return mPageCount;
    }

}
