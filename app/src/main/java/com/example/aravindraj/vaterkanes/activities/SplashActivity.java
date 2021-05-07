package com.example.aravindraj.vaterkanes.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aravindraj.vaterkanes.R;


public class SplashActivity extends AppCompatActivity {

    public static final int splash_timeout = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        init();
    }

    private void init() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToDashboard();
            }
        },splash_timeout);

    }

    private void goToDashboard() {
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);

    }
    private void snakbar()
    {

    }
}
