package com.somaprojexts.projectsashimi.M0_AppStart;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.somaprojexts.projectsashimi.R;
import com.somaprojexts.projectsashimi.Adapters.SectionsStatePagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    protected static ViewPager m0ViewPager;
    private static SectionsStatePagerAdapter adapter;
    private static List<String> m0FragmentNameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        m0ViewPager = findViewById(R.id.m0_fragment_container);
        setupViewPager(m0ViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new M0_EntryNoAcc_Frag(), "M0_EntryNoAcc_Frag");

        m0FragmentNameList = adapter.getFragmentNameList();
        viewPager.setAdapter(adapter);
    }

    public void setFragment(String fragmentName) {
        Log.i(TAG, "SetFragment");
        this.m0ViewPager.setCurrentItem(m0FragmentNameList.indexOf(fragmentName));
    }


}