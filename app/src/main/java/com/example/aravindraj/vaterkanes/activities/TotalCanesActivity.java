package com.example.aravindraj.vaterkanes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aravindraj.vaterkanes.R;
import com.example.aravindraj.vaterkanes.additional_classes.ConstantsClasses;

public class TotalCanesActivity extends AppCompatActivity {

    ConstantsClasses[] constantsClasses = ConstantsClasses.values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_canes);

        for(ConstantsClasses constantsClasseses : constantsClasses){
            constantsClasseses.valueOf(constantsClasseses.toString());

        }


    }
}
