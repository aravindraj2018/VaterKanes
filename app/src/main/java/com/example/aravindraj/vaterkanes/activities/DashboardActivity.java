package com.example.aravindraj.vaterkanes.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.aravindraj.vaterkanes.R;


public class DashboardActivity extends AppCompatActivity {

    private LinearLayout grid5;
    private LinearLayout grid6;
    private LinearLayout grid1;
    private LinearLayout grid2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        setOnclickListener();
    }

    private void init() {

        grid1 = (LinearLayout) findViewById(R.id.grid1);
        grid2 = (LinearLayout) findViewById(R.id.grid2);
        grid5 = (LinearLayout) findViewById(R.id.grid5);
        grid6 = (LinearLayout) findViewById(R.id.grid6);
    }

    private void setOnclickListener() {

        grid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,TotalCanesActivity.class);
                startActivity(intent);
            }
        });

        grid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,CaneConditionActivity.class);
                startActivity(intent);
                }
        });
        grid5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,AddCustomerActivity.class);
                startActivity(intent);

            }
        });

        grid6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ExistingCustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
