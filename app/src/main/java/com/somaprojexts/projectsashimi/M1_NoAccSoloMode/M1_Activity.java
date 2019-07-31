package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.somaprojexts.projectsashimi.Adapters.SectionsStatePagerAdapter;
import com.somaprojexts.projectsashimi.R;

import java.util.List;

public class M1_Activity extends AppCompatActivity {

    private static final String TAG = "M1_Activity.java";

    protected static ViewPager m1ViewPager;
    private static SectionsStatePagerAdapter adapter;
    private static List<String> m1FragmentNameList;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: Started.");

        setContentView(R.layout.m1_activity_layout);

        m1ViewPager = findViewById(R.id.m1_fragment_container);
        setupViewPager(m1ViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new M1_PrefSelect_Frag(), "M1_PrefSelect_Frag");
        adapter.addFragment(new M1_SwipeDash_Frag(), "M1_SwipeDash_Frag");
        adapter.addFragment(new M1_NoMoreOptions_Frag(), "M1_NoMoreOptions_Frag");
        adapter.addFragment(new M1_Details_Frag(), "M1_Details_Frag");
        adapter.addFragment(new M1_Favorites_Frag(), "M1_Favorites_Frag");
        adapter.addFragment(new M1_FavDetails_Frag(), "M1_FavDetails_Frag");
        adapter.addFragment(new M1_Map_Frag(), "M1_Map_Frag");

        m1FragmentNameList = adapter.getFragmentNameList();
        viewPager.setAdapter(adapter);
    }

    public static void setFragment(String fragmentName) {
        Log.i(TAG, "SetFragment");
        m1ViewPager.setCurrentItem(m1FragmentNameList.indexOf(fragmentName));
    }
}
