
package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.somaprojexts.projectsashimi.R;

public class M1_Map_Frag extends Fragment {

    private static final String TAG = "M1_Map_Frag";
    private TextView btn_dev_back;
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

        btn_dev_back = view.findViewById(R.id.btn_dev_back);
        btn_dev_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_Activity.setFragment("M1_Favorites_Frag");
            }
        });

        return view;

    }


}
