package com.somaprojexts.projectsashimi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_NoAccDash extends Fragment {

    private static final String TAG = "Fragment_NoAccDash";

    private TextView btn_goSolo;
    private TextView btn_goWithFriends;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Creates chosen xml file -- stores it in a view
        View view = inflater.inflate(R.layout.fragment_noaccdash_layout, container, false);

        Log.d(TAG, "OnCreate: started");

        btn_goSolo = view.findViewById(R.id.btn_go_solo);
        btn_goWithFriends = view.findViewById(R.id.btn_go_with_friends);

        return view;
    }
}
