package com.example.aravindraj.vaterkanes.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aravindraj.vaterkanes.DataBaseHelperClass.DataBaseHelperClass;
import com.example.aravindraj.vaterkanes.R;
import com.example.aravindraj.vaterkanes.adapter.ExistingCustomersListAdapter;
import com.example.aravindraj.vaterkanes.model.NewCustomerListData;

import java.util.ArrayList;

public class ExistingCustomerActivity extends AppCompatActivity {

    private ImageView back_button;
    private ImageView titleLayout_Right_ImageView;
    private DataBaseHelperClass dataBaseHelperClass;
    ExistingCustomersListAdapter customersListAdapter;
    RecyclerView recyclerView;

    // ArrayList of new customers data
    ArrayList<NewCustomerListData> newCustomerListData = new ArrayList<>();
    private TextView titleLayout_main_TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_customer);

        init();
        setOnclickListener();
        getDbValues();
        setTextView();
        editDeleteOption();
    }




    private void init() {
        // initializing data base
        dataBaseHelperClass = new DataBaseHelperClass(ExistingCustomerActivity.this);


        back_button = (ImageView) findViewById(R.id.back_button);
        titleLayout_Right_ImageView = (ImageView) findViewById(R.id.titleLayout_Right_ImageView);
        titleLayout_Right_ImageView.setVisibility(View.VISIBLE);
        titleLayout_Right_ImageView.setImageResource(R.mipmap.ic_event_add);
        recyclerView = (RecyclerView) findViewById(R.id.existingCustomerRecyclerview);
        titleLayout_main_TextView = (TextView) findViewById(R.id.titleLayout_main_TextView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExistingCustomerActivity.this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void setOnclickListener() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        titleLayout_Right_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExistingCustomerActivity.this,AddCustomerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getDbValues() {
        dataBaseHelperClass = new DataBaseHelperClass(ExistingCustomerActivity.this);
        newCustomerListData = dataBaseHelperClass.getNewCustomerListData();
        if(newCustomerListData.size()>0){
            // go to adapter to get existing customer arraylist
            customersListAdapter = new ExistingCustomersListAdapter(ExistingCustomerActivity.this,newCustomerListData);
            recyclerView.setAdapter(customersListAdapter);
            customersListAdapter.notifyDataSetChanged();


        }else {
            Toast.makeText(this, "No Customers are Available", Toast.LENGTH_SHORT).show();
        }


    }

    private void setTextView() {
        titleLayout_main_TextView.setText("Existing Customer");
    }

    private void editDeleteOption() {
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog dialog = new Dialog(ExistingCustomerActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.customer_edit_delete_dialog);
                dialog.show();

                return true;

            }
        });

    }
}
