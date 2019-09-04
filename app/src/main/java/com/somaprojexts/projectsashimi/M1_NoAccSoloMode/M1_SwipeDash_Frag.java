package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.somaprojexts.projectsashimi.R;
import com.yuyakaido.android.cardstackview.CardStackView;

public class M1_SwipeDash_Frag extends Fragment {

    private static final String TAG = "M1_SwipeDash_Frag";

    private CardStackView card_stack;
    private ProgressBar progress_bar;
    private TextView btn_dev_swiperight;
    private TextView btn_dev_swipeup;
    private TextView btn_dev_swipedown;
    private TextView btn_dev_nomoreoptions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_swipedash_frag_layout, container, false);

        btn_dev_swiperight = view.findViewById(R.id.btn_dev_swiperight4);
        btn_dev_swipeup = view.findViewById(R.id.btn_dev_swipeup);
        btn_dev_swipedown = view.findViewById(R.id.btn_dev_swipedown);
        btn_dev_nomoreoptions = view.findViewById(R.id.btn_dev_nomoreoptions);

        btn_dev_swiperight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast addedFavToast = Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT);
                addedFavToast.show();
            }
        });

        btn_dev_swipeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M1_Activity.setFragment("M1_Details_Frag");
            }
        });

        btn_dev_swipedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M1_Activity.setFragment("M1_Favorites_Frag");
            }
        });

        btn_dev_nomoreoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M1_NoMoreOptions_Frag dialog = new M1_NoMoreOptions_Frag();
                dialog.setCancelable(true);
                dialog.show(getFragmentManager(), "M1_NoMoreOptions_Frag");
            }
        });

        card_stack = view.findViewById(R.id.card_stack);
        progress_bar = view.findViewById(R.id.progress_bar);

        M1_Activity m1Activity = (M1_Activity) getActivity();
        card_stack.setLayoutManager(m1Activity.getCardStackLayoutManager());
        card_stack.setAdapter(m1Activity.getCardStackAdapter());

        return view;
    }

    CardStackView getCard_stack() {
        return card_stack;
    }

    ProgressBar getProgress_bar() {
        return progress_bar;
    }
}
