package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.somaprojexts.projectsashimi.M0_AppStart.MainActivity;
import com.somaprojexts.projectsashimi.R;

public class M1_SwipeDash_Frag extends Fragment {

    private static final String TAG = "M1_SwipeDash_Frag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_swipedash_frag_layout, container, false);

        Log.i(TAG, "onCreate: Finished");

        return view;
    }


}
