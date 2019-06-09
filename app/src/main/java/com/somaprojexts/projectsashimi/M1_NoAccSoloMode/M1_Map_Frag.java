
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

public class M1_Map_Frag extends Fragment {

    private static final String TAG = "M1_Map_Frag";
    private TextView btn_devback;
    private TextView btn_startover;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_map_frag_layout , container, false);

        btn_startover = view.findViewById(R.id.btn_8a);
        btn_startover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_StartOverWarning_Frag dialog = new M1_StartOverWarning_Frag();
                dialog.setCancelable(true);
                dialog.show(getFragmentManager(), "M1_StartOverWarning_Frag");
            }
        });

        btn_devback = view.findViewById(R.id.btn_devback);
        btn_devback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_Acti.setFragment("M1_Favorites_Frag");
            }
        });

        return view;

    }


}
