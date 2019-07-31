package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.somaprojexts.projectsashimi.R;
import com.somaprojexts.projectsashimi.card.Card;
import com.somaprojexts.projectsashimi.card.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

public class M1_SwipeDash_Frag extends Fragment {

    private static final String TAG = "M1_SwipeDash_Frag";

    private TextView btn_devswiperight;
    private TextView btn_devswipeup;
    private TextView btn_devswipedown;
    private TextView btn_devnomoreoptions;
    private CardStackView cardStackView;
    private Card[] cards = {
            new Card("Daniel Hodek", R.drawable.photo_1),
            new Card("Lena Shipseki", R.drawable.photo_2),
            new Card("Perry Centaur", R.drawable.photo_3)
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_swipedash_frag_layout, container, false);

        btn_devswiperight = view.findViewById(R.id.btn_devswiperight);
        btn_devswipeup = view.findViewById(R.id.btn_devswipeup);
        btn_devswipedown = view.findViewById(R.id.btn_devswipedown);
        btn_devnomoreoptions = view.findViewById(R.id.btn_devnomoreoptions);

        btn_devswiperight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast addedFavToast = Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT);
                addedFavToast.show();
            }
        });

        btn_devswipeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M1_Activity.setFragment("M1_Details_Frag");
            }
        });

        btn_devswipedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M1_Activity.setFragment("M1_Favorites_Frag");
            }
        });

        btn_devnomoreoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M1_NoMoreOptions_Frag dialog = new M1_NoMoreOptions_Frag();
                dialog.setCancelable(true);
                dialog.show(getFragmentManager(), "M1_NoMoreOptions_Frag");
            }
        });

        cardStackView = view.findViewById(R.id.card_stack);
        cardStackView.setLayoutManager(new CardStackLayoutManager(getContext()));
        cardStackView.setAdapter(new CardStackAdapter(getContext(), cards));

        return view;
    }
}
