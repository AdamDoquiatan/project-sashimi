
package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.somaprojexts.projectsashimi.M0_AppStart.M0_EntryNoAcc_Frag;
import com.somaprojexts.projectsashimi.R;

public class M1_StartOverWarning_Frag extends DialogFragment {

    private static final String TAG = "M1_StartOverWarn_Frag";
    private TextView btn_4a;
    private TextView btn_4b;
    private ImageView closeTrigger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "onCreate: Started");

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m1_startoverwarning_frag_popup, container, false);

        btn_4a = view.findViewById(R.id.btn_9a);
        btn_4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent(getActivity(), M0_EntryNoAcc_Frag.class);
                startActivity(intent);
            }
        });

        btn_4b = view.findViewById(R.id.btn_9b);
        btn_4b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        closeTrigger = view.findViewById(R.id.closeTrigger);
        closeTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogFrag);
        // STYLE_NO_FRAME means that I will provide my own layout and style for the dialog (R.style.DialogFrag)
    }
}
