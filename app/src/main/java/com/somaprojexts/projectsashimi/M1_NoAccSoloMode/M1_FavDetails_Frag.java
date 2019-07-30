
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

public class M1_FavDetails_Frag extends Fragment {

    private static final String TAG = "M1_FavDetails_Frag";
    private TextView btn_devback;
    private TextView btn_showonmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_favdetails_frag_layout , container, false);

        btn_showonmap = view.findViewById(R.id.btn_7a);
        btn_showonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_Activity.setFragment("M1_Map_Frag");
            }
        });

        btn_devback = view.findViewById(R.id.btn_devback);
        btn_devback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_Activity.setFragment("M1_Favorites_Frag");
            }
        });

        return view;

    }


}
