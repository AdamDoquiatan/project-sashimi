package com.somaprojexts.projectsashimi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class Activity_NoAccSolo extends Activity {

    private static final String TAG = "Activity_NoAccSolo.java";

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_noaccsolo_layout);

        Log.d(TAG, "onCreate: Started.");
    }


}
