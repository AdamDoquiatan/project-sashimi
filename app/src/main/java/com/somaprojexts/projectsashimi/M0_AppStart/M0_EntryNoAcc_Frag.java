package com.somaprojexts.projectsashimi.M0_AppStart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.somaprojexts.projectsashimi.M1_NoAccSoloMode.M1_Acti;
import com.somaprojexts.projectsashimi.R;

public class M0_EntryNoAcc_Frag extends Fragment {

    private static final String TAG = "M0_EntryNoAcc_Frag";

    private TextView btn_goSolo;
    private TextView btn_goWithFriends;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.m0_entrynoacc_frag_layout, container, false);

        Log.i(TAG, "OnCreate: started");

        btn_goSolo = view.findViewById(R.id.btn_go_solo);
        btn_goWithFriends = view.findViewById(R.id.btn_go_with_friends);

        btn_goSolo.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Log.i("btn_goSolo","clicked");

                Intent intent = new Intent(getActivity(), M1_Acti.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
