package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.somaprojexts.projectsashimi.R;

public class M1_PrefSelect_Frag extends Fragment {

    private static final String TAG = "M1_PrefSelect_Frag";

    private Button btn_dev_next;
    private Button btn_search;
    private TextView distance_label;
    private SeekBar distance_seek;
    private EditText term_input;

    private M1_Activity m1Activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: Started");
        View view = inflater.inflate(R.layout.m1_prefselect_frag_layout, container, false);

        m1Activity = (M1_Activity) getActivity();

        btn_dev_next = view.findViewById(R.id.btn_dev_next);
        btn_search = view.findViewById(R.id.btn_search);
        term_input = view.findViewById(R.id.term_input);

        btn_dev_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                M1_Activity.setFragment("M1_SwipeDash_Frag");
            }
        });

        btn_search.setOnClickListener(v -> {
            m1Activity.clearCards();
            m1Activity.doYelpSearch();
            M1_Activity.setFragment("M1_SwipeDash_Frag");
        });

        term_input.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        m1Activity.setTerm(v.getText().toString());
                    }
                }
                return false;
            }
        });

        distance_seek = view.findViewById(R.id.distance_seek);
        distance_seek.setOnSeekBarChangeListener(new DistanceSeekBarChangeListener());
        distance_label = view.findViewById(R.id.distance_label);
        distance_seek.setProgress(m1Activity.RADIUS_DEFAULT);

        return view;
    }

    private class DistanceSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String newText = getResources()
                    .getString(R.string.distance_label) + " " + progress + " km";
            Log.i(TAG, "newText=" + newText);
            distance_label.setText(newText);
            m1Activity.setRadius(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
