package com.somaprojexts.projectsashimi.M0_AppStart;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.somaprojexts.projectsashimi.R;
import com.somaprojexts.projectsashimi.util.SectionsStatePagerAdapter;

import java.util.List;

public class M0_Activity extends AppCompatActivity {

    private static final String TAG = "M0_Activity";

    private static ViewPager viewPager;
    private static SectionsStatePagerAdapter adapter;
    private static List<String> fragmentNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.m0_fragment_container);
        setupViewPager(viewPager);

        // Request permissions
        requestPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, 1);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new M0_EntryNoAcc_Frag(), "M0_EntryNoAcc_Frag");

        fragmentNameList = adapter.getFragmentNameList();
        viewPager.setAdapter(adapter);
    }

    public void setFragment(String fragmentName) {
        Log.i(TAG, "SetFragment");
        viewPager.setCurrentItem(fragmentNameList.indexOf(fragmentName));
    }
}