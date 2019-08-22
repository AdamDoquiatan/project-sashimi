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

    public void addFragment(Fragment fragment, String fragmentTitle) {
        fragmentList.add(fragment);
        fragmentNameList.add(fragmentTitle);
    }

    public List<String> getFragmentNameList() {
        return fragmentNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public Fragment getItem(String fragmentTitle) {
        return fragmentList.get(fragmentNameList.indexOf(fragmentTitle));
    }

    @Override
    public int getCount() {
        return fragmentNameList.size();
    }
}
