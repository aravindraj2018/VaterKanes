package com.example.aravindraj.vaterkanes.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aravindraj.vaterkanes.DataBaseHelperClass.DataBaseHelperClass;
import com.example.aravindraj.vaterkanes.R;
import com.example.aravindraj.vaterkanes.model.NewCustomerListData;

import java.util.ArrayList;


public class AddCustomerActivity extends AppCompatActivity {

    private ImageView backbutton;
    private TextView titleLayout_main_TextView;
    private EditText customerName;
    private EditText customerPhoneNo;
    private EditText customerAddress;
    private ImageView customerImage;
    private Button submitButton;
    private Button cancelButton;
    DataBaseHelperClass dataBaseHelperClass;
    private String new_customer_name;
    private String new_customer_phoneNo;
    private String new_customer_address;
    private AlertDialog.Builder dialoge;

    // ArrayList of new customers data
    ArrayList<NewCustomerListData> newCustomerListData = new ArrayList<>();

//testing.......
    //testing2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        init();
        onclickListener();
        setText();
    }



    private void init() {
        // initialzing database
        dataBaseHelperClass = new DataBaseHelperClass(AddCustomerActivity.this);


        customerName = (EditText) findViewById(R.id.customer_Name);
        customerPhoneNo = (EditText) findViewById(R.id.customerPhoneNo);
        customerAddress = (EditText) findViewById(R.id.customerAddress);
        backbutton = (ImageView) findViewById(R.id.back_button);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        titleLayout_main_TextView = (TextView) findViewById(R.id.titleLayout_main_TextView);


    }

    private void onclickListener() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check for empty data
                if(checkEmptyData()){
                    // go to add local database after checking empty condition
                    dataBaseHelperClass = new DataBaseHelperClass(AddCustomerActivity.this);
                     new_customer_name = customerName.getText().toString();
                     new_customer_phoneNo = customerPhoneNo.getText().toString();
                     new_customer_address = customerAddress.getText().toString();

                     // sending new customer data to the local database class
                    dataBaseHelperClass.insertNewCustomer(new_customer_name,new_customer_phoneNo,
                            new_customer_address);
                    clearTextFieldData();


                    /*newCustomerListData = dataBaseHelperClass.getNewCustomerListData();
                    Toast.makeText(AddCustomerActivity.this, ""+newCustomerListData,
                            Toast.LENGTH_SHORT).show();*/





                }else {
                   // need to think do something
                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // give alert that entered data will be cleared by clicking cancel

               // checking atleast on text field is not empty
                if(ischeckForEmptyData()){
                    // open alert dialog box
                    openAlertDialoge();
                }
            }
        });

       /* customerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // need to go to gallery to take image of customer if available
            }
        });*/
    }

    private void openAlertDialoge() {

        dialoge = new AlertDialog.Builder(AddCustomerActivity.this);
        dialoge.setTitle("Alert Message");
        dialoge.setMessage("By Clicking Ok Entered data will cleared");
        dialoge.setMessage("Are you sure you want to clear the data ?");

        dialoge.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                customerName.getText().clear();
                customerPhoneNo.getText().clear();
                customerAddress.getText().clear();
                dialog.dismiss();

            }
        });

        dialoge.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        dialoge.show();
    }

    private boolean ischeckForEmptyData() {
        if(customerName.getText().toString().equals("") &&(customerPhoneNo.getText().toString().equals("")) &&(customerAddress.getText().toString().equals(""))){
            Toast.makeText(AddCustomerActivity.this, "Please Enter Atleast One Data", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private boolean checkEmptyData() {
        if(customerName.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Customer Name", Toast.LENGTH_SHORT).show();
            return false;
        }else if(customerPhoneNo.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Customer Phone No", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void setText() {
        titleLayout_main_TextView.setText("Add New Customer");
    }

    private void clearTextFieldData() {
        // clearing text in the edit box after adding a new customer in local database
        customerName.getText().clear();
        customerPhoneNo.getText().clear();
        customerAddress.getText().clear();

        // showing dialog for success message
        dialoge = new AlertDialog.Builder(this);
        dialoge.setTitle("Message");
        dialoge.setMessage("New Customer added successfully");

        dialoge.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        dialoge.show();
    }


}
