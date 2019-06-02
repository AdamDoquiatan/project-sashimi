
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

import com.somaprojexts.projectsashimi.R;

public class M1_NoMoreOptions_Frag extends Fragment {

    private static final String TAG = "";
    private TextView btn_devback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_nomoreoptions_frag_popup, container, false);


        btn_devback = view.findViewById(R.id.btn_devback);

        btn_devback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_Acti.setFragment("M1_SwipeDash_Frag");
            }
        });

        return view;

    }


}
