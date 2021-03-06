package com.somaprojexts.projectsashimi.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentNameList = new ArrayList<>();

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment addFragment(Fragment fragment, String fragmentTitle) {
        fragmentList.add(fragment);
        fragmentNameList.add(fragmentTitle);
        return fragment;
    }

    public List<String> getFragmentNameList() {
        return fragmentNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentNameList.size();
    }
}
